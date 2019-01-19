package napior.calculations;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationsController {

    @PostMapping("/calculation/wood-beam")
    public BeamCalculation calculateWoodBeam(@RequestBody BeamCalculationRequestBody beamCalcInputs){
        BeamCalculation beamCalculation = new BeamCalculation(beamCalcInputs);
        return beamCalculation;
    }
}
