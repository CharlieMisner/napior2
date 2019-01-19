package napior.calculations;

public class BeamCalculationRequestBody {
    private BeamInput[] beamInputs;
    private Node[] nodeInputs;

    public BeamInput[] getBeamInputs() {
        return beamInputs;
    }

    public Node[] getNodeInputs() {
        return nodeInputs;
    }
}
