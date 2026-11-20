import dev.khayal.idtech.courier.entity.CourierEntity

import static dev.khayal.idtech.courier.enums.CourierStatus.BUSY
import static dev.khayal.idtech.courier.enums.CourierStatus.FREE;
import dev.khayal.idtech.courier.mapper.CourierMapper
import dev.khayal.idtech.courier.repository.CourierRepository
import dev.khayal.idtech.courier.service.CourierService
import spock.lang.Specification

class CourierServiceSpec extends Specification {
    private CourierRepository courierRepository;
    private CourierMapper courierMapper;
    private CourierService courierService;

    def setup() {
        courierRepository = Mock();
        courierMapper = Mock();
        courierService = new CourierService(courierRepository, courierMapper);
    }

    def "Mark a courier busy successfully"() {
        given:
            Long id = 1L;
            var courier = new CourierEntity(id: id, status: FREE);
        when:
            courierService.markBusy(id);
        then:
            1 * courierRepository.findByIdAndStatus(id, FREE) >> Optional.of(courier);
            1 * courierRepository.save(courier)
            courier.status == BUSY;
    }

}
