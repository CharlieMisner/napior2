package napior.calculations;

import java.util.ArrayList;

public class BeamCalculation {

    private static final int NODE_DEGREES_OF_FREEDOM = 3;
    private static final int BEAM_DEGREES_OF_FREEDOM = 6;

    private BeamInput[] beamInputs;
    private ArrayList<Beam> beams;
    private Node[] nodeInputs;

    public BeamCalculation(BeamCalculationRequestBody calculationInputs) {
        this.beamInputs = calculationInputs.getBeamInputs();
        this.nodeInputs = calculationInputs.getNodeInputs();
        this.beams = instantiateBeams(beamInputs);
    }

    /**
     * Instantiates beam objects from inputs.
     * @param beamInputs
     * @return
     */
    private ArrayList<Beam> instantiateBeams(BeamInput[] beamInputs) {
        ArrayList<Beam> beamsArray = new ArrayList<Beam>();
        for(BeamInput beamInput: beamInputs){
            Node startNode = nodeInputs[beamInput.getStartNodeId()];
            Node endNode = nodeInputs[beamInput.getEndNodeId()];
            beamsArray.add(new Beam(beamInput, startNode, endNode));
        }
        return beamsArray;
    }


    public Node[] getNodes() {
        return nodeInputs;
    }

    public ArrayList<Beam> getBeams() {
        return beams;
    }
}
