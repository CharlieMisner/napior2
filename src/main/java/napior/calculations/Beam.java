package napior.calculations;

public class Beam {

    private String beamType;
    private Node startNode;
    private Node endNode;
    private double length;
    private double beamArea;
    private double beamElasticModulus;
    private double beamMomentOfInertia;
    private ElementStiffnessMatrix stiffnessMatrix;

    public Beam(BeamInput beamInput, Node startNode, Node endNode) {
        this.beamType = beamInput.getBeamType();
        this.beamArea = beamInput.getArea();
        this.beamElasticModulus = beamInput.getElasticModulus();
        this.beamMomentOfInertia = beamInput.getMomentOfInertia();
        this.startNode = startNode;
        this.endNode = endNode;
        this.length = calculateBeamLength(this.startNode, this.endNode);
        this.stiffnessMatrix = new ElementStiffnessMatrix(this);
    }

    /**
     * Returns the length of a beam based on the start and end node coords.
     * @param startNode
     * @param endNode
     * @return
     */
    private double calculateBeamLength(Node startNode, Node endNode) {
        double x0 = startNode.getxCoordinate();
        double x1 = endNode.getxCoordinate();
        double y0 = startNode.getyCoordinate();
        double y1 = endNode.getyCoordinate();
        return Math.sqrt(Math.pow(x0 - x1, 2) + Math.pow(y0 - y1, 2));
    }

    public String getBeamType() {
        return beamType;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public double getLength() {
        return length;
    }

    public double getBeamArea() {
        return beamArea;
    }

    public double getBeamElasticModulus() {
        return beamElasticModulus;
    }

    public double getBeamMomentOfInertia() {
        return beamMomentOfInertia;
    }

    public String getStiffnessMatrix() {
        return stiffnessMatrix.toString();
    }
}
