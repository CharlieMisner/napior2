package napior.calculations;

public class BeamInput {
    private String beamType;
    private int startNodeId;
    private int endNodeId;
    private int area;
    private int elasticModulus;
    private int momentOfInertia;


    public String getBeamType() {
        return beamType;
    }

    public int getStartNodeId() {
        return startNodeId;
    }

    public int getEndNodeId() {
        return endNodeId;
    }

    public int getArea() { return area; }

    public int getElasticModulus() {
        return elasticModulus;
    }

    public int getMomentOfInertia() {
        return momentOfInertia;
    }
}
