package com.dev.tire;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
// TireService is the layer between the API and the data management.
public class TireService {

    private final TireRepository tireRepository;
    private final RaceRepository raceRepository;
    private final TireSetRepository tireSetRepository;


    @Autowired
    public TireService(TireRepository tireRepository, RaceRepository raceRepository, TireSetRepository tireSetRepository) {
        this.tireRepository = tireRepository;
        this.raceRepository = raceRepository;
        this.tireSetRepository = tireSetRepository;
    }

    /*##########################################################################################################
     *  methodes for GET requests
     */
    public List<com.dev.tire.Tire> getTires() {
        List<Tire> tires = (List<Tire>) tireRepository.findAll();
        if (tires.isEmpty()) {
            throw new IllegalStateException("No tires were found.");
        }
        return tires;
    }

    public List<Tire> findTiresByBezeichnung(String bezeichnung) {
        List<Tire> tires = tireRepository.findTiresByBezeichnung(bezeichnung);
        if (tires.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with Bezeichnung: %s", bezeichnung));
        }
        return tires;
    }

    public Optional<Tire> findTireById(Long tireID) {
        Optional<Tire> tire = tireRepository.findTireByTireID(tireID);
        if (tire.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with TireID: %s", tireID));
        }
        return tire;
    }

    public Optional<Tire> findTireBySerialnumber(String serialnumber) {
        Optional<Tire> tire = tireRepository.findTireBySerialNumber(serialnumber);
        if (tire.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with Serialnumber: %s", serialnumber));
        }
        return tire;
    }

    public List<Tire> findTiresByTime(Time time) {
        List<Tire> tires = tireRepository.findTiresByErhaltenUm(time);
        if (tires.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with time: %s", time));
        }
        return tires;
    }

    public List<Tire> findTiresByStatus(String status) {
        List<Tire> tires = tireRepository.findTiresByStatus(status);
        if (tires.isEmpty()) {
            throw new IllegalStateException(String.format("No tires with status %s were found.", status));
        }
        return tires;
    }

    /*##########################################################################################################
     *  methodes for POST requests
     */
    public Tire addNewTire(Tire tire) {
        return tireRepository.save(tire);
    }


    public void addNewTireSet(Long raceid, TireDto tireDto) {
        Optional<Race> race;
        if (raceid != null) {
            race = raceRepository.findRaceByRaceID(raceid);
            if (race.isEmpty()) {
                throw new IllegalStateException(String.format("No race with ID %s was found.", raceid));
            }
        } else {
            race = raceRepository.findFirstByOrderByDateDescRaceIDDesc();
            if (race.isEmpty()) {
                throw new IllegalStateException("No race available.");
            }
        }
        Optional<TireSet> lastSet = tireSetRepository.findFirstByRace_RaceIDEqualsOrderByTireSetNrDesc(race.get().getRaceID());
        int nr;
        if (lastSet.isEmpty()) {
            nr = 1;
        } else {
            nr = lastSet.get().getTireSetNr() + 1;
        }
        Tire FR = new Tire(tireDto.getFrontMischung(), tireDto.getFrontArt());
        FR.setBezeichnung(String.format("%s%02d", race.get().getPrefixes().getprefix(FR.getMischung()), nr));
        Tire FL = new Tire(tireDto.getFrontMischung(), tireDto.getFrontArt());
        FL.setBezeichnung(String.format("%s%02d", race.get().getPrefixes().getprefix(FL.getMischung()), nr));
        Tire RL = new Tire(tireDto.getRearMischung(), tireDto.getRearArt());
        RL.setBezeichnung(String.format("%s%02d", race.get().getPrefixes().getprefix(RL.getMischung()), nr));
        Tire RR = new Tire(tireDto.getRearMischung(), tireDto.getRearArt());
        RR.setBezeichnung(String.format("%s%02d", race.get().getPrefixes().getprefix(RR.getMischung()), nr));

        TireSet tireSet = new TireSet(nr);
        tireSet.setRace(race.get());
        tireSet = tireSetRepository.save(tireSet);
        RR.setTireSet(tireSet);
        RL.setTireSet(tireSet);
        FL.setTireSet(tireSet);
        FR.setTireSet(tireSet);
        tireRepository.save(FR);
        tireRepository.save(FL);
        tireRepository.save(RR);
        tireRepository.save(RL);

    }

    /*##########################################################################################################
     *  methodes for DELETE requests
     */
    public void deleteTire(Long tireID) {
        if (!tireRepository.existsById(tireID)) {
            throw new IllegalStateException(String.format("tire with id %s does not exist.", tireID));
        } else {
            tireRepository.deleteById(tireID);
        }
    }

    /*##########################################################################################################
     *  methodes for PUT requests
     */

    @Transactional
    public Tire changeStatus(Long tireid, String newStatus) {
        Optional<Tire> tire = tireRepository.findTireByTireID(tireid);
        if (newStatus.equals("bestellt") || newStatus.equals("auf lager") || newStatus.equals("benutzt")) {
            tire.ifPresentOrElse(tire1 -> tire1.setStatus(newStatus), () -> {
                throw new IllegalStateException(String.format("No tire with id %s was found.", tireid));
            });
        } else {
            throw new IllegalStateException(String.format("No status named %s is available.", newStatus));
        }

        return tire.get();
    }

    @Transactional
    // This methode checks every given argument for existence and equality to the tire field and replaces the tire field if necessary
    public Tire updateTire(Long tireID, String serial, String bez,
                           String mischung, String art, Time erhalten_um,
                           String session, Optional<Double> kaltdruck, Optional<Integer> kaltdruckTemp,
                           Optional<Integer> heatingTemp, Optional<Integer> heatingTime, Time heatingStart,
                           Time heatingStop) {
        Tire tire = tireRepository.findTireByTireID(tireID).orElseThrow(() ->
                new IllegalStateException(String.format("Tire with id %s could not be found.", tireID)));
        if (serial != null && serial.length() > 0 && !tire.serialNumber.equals(serial)) {
            tire.setSerialNumber(serial);
        }
        if (bez != null && bez.length() > 0 && !tire.bezeichnung.equals(bez)) {
            tire.setBezeichnung(bez);
        }
        if (mischung != null && mischung.length() > 0 && !tire.mischung.equals(mischung)) {
            tire.setMischung(mischung);
        }
        if (art != null && art.length() > 0 && !tire.art.equals(art)) {
            tire.setArt(art);
        }
        if (erhalten_um != null && tire.erhaltenUm != erhalten_um) {
            tire.setErhaltenUm(erhalten_um);
        }
        if (session != null && session.length() > 0 && !tire.session.equals(session)) {
            tire.setSession(session);
        }
        kaltdruck.ifPresent(tire::setKaltdruck);
        kaltdruckTemp.ifPresent(tire::setKaltdruckTemp);
        heatingTemp.ifPresent(tire::setHeatingTemp);
        heatingTime.ifPresent(tire::setHeatingTime);
        if (heatingStart != null && tire.heatingStart != heatingStart) {
            tire.setHeatingStart(heatingStart);
        }
        if (heatingStop != null && tire.heatingStop != heatingStop) {
            tire.setHeatingStop(heatingStop);
        }

        return tire;
    }


}

