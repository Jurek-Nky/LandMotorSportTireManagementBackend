package com.dev.tire;

import com.dev.race.Race;
import com.dev.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
// TireService is the layer between the API and the data management.
public class TireService {

    private final TireRepository tireRepository;
    private final RaceRepository raceRepository;


    @Autowired
    public TireService(TireRepository tireRepository, RaceRepository raceRepository) {
        this.tireRepository = tireRepository;
        this.raceRepository = raceRepository;
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

    public List<Tire> findTiresByRennId(Long raceID) {
        List<Tire> tires = tireRepository.findTiresByRace_RaceID(raceID);
        if (tires.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with RennID: %s", raceID));
        }
        return tires;
    }

    public List<Tire> findTiresByTime(Time time) {
        List<Tire> tires = tireRepository.findTiresByErhaltenUm(time);
        if (tires.isEmpty()) {
            throw new IllegalStateException(String.format("No tires were found with time: %s", time));
        }
        return tires;
    }

    /*##########################################################################################################
     *  methodes for POST requests
     */
    public Tire addNewTire(Tire tire) {
        return tireRepository.save(tire);
    }

    public Tire addNewTire(TireDto tireDto) {
        System.out.println("service");
        // adding tire using DataTransferObject to make it easier to work with foreign fields.
        Optional<Race> race = raceRepository.findRaceByRaceID(tireDto.raceid);
        if (race.isEmpty()) {
            throw new IllegalStateException(String.format("No race with raceID %s found.", tireDto.raceid));
        }
//        Optional<Tire> tireSerialTest = tireRepository.findTireBySerialNumber(tireDto.serialNumber);
//        if (tireSerialTest.isPresent()) {
//            throw new IllegalStateException(String.format("Tire with serialnumber %s already exists.", tireDto.serialNumber));
//        }
        Tire tire = new Tire(
                race.get(),
                tireDto.serialNumber,
                tireDto.bezeichnung,
                tireDto.mischung,
                tireDto.art);

        return tireRepository.save(tire);

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
    public Tire changeStatus(Long tireid,String newStatus){
        Optional<Tire> tire = tireRepository.findTireByTireID(tireid);
        tire.ifPresentOrElse(tire1 -> {tire1.setStatus(newStatus);},
                () -> {throw  new IllegalStateException(String.format("No tire with id %s was found.",tireid));});
        return tire.get();
    }

    @Transactional
    // This methode checks every given argument for existence and equality to the tire field and replaces the tire field if necessary
    public Tire updateTire(Long tireID, Long raceID, String serial, String bez,
                           String mischung, String art, Time erhalten_um,
                           String session, Optional<Double> kaltdruck, Optional<Integer> kaltdruckTemp,
                           Optional<Integer> heatingTemp, Optional<Integer> heatingTime, Time heatingStart,
                           Time heatingStop) {
        Tire tire = tireRepository.findTireByTireID(tireID).orElseThrow(() ->
                new IllegalStateException(String.format("Tire with id %s could not be found.", tireID)));
        if (raceID != null && !Objects.equals(tire.race.getRaceID(), raceID)) {
            Optional<Race> race = raceRepository.findRaceByRaceID(raceID);
            race.ifPresentOrElse(tire::setRace,
                    () -> {
                        throw new IllegalStateException(String.format("No race with id %s was found.", raceID));
                    });
        }
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

