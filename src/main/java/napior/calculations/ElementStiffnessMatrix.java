package napior.calculations;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class ElementStiffnessMatrix {

    private Beam beam;
    private RealMatrix localElementStiffnessMatrix;
    private RealMatrix elementTransformationMatrix;
    private RealMatrix globalElementStiffnessMatrix;

    public ElementStiffnessMatrix(Beam beam) {
        this.beam = beam;
        this.localElementStiffnessMatrix = this.assembleLocalElementStiffnessMatrix();
        this.elementTransformationMatrix = this.assembleElementTransformationMatrix();
        this.globalElementStiffnessMatrix = this.transformLocalToGlobal();
    }

    /**
     * Assembles a local area stiffness matrix using the beam properties.
     * @return
     */
    private RealMatrix assembleLocalElementStiffnessMatrix(){
        double A = beam.getBeamArea();
        double E = beam.getBeamElasticModulus();
        double I = beam.getBeamMomentOfInertia();
        double L = beam.getLength();

        double a = A*E/L;
        double b = 12*E*I/Math.pow(L,3);
        double c = 6*E*I/Math.pow(L,2);
        double d = 4*E*I/L;
        double e = 2*E*I/L;

        double[][] matrixData = {
            { a,  0 , 0, -a,  0,  0},
            { 0,  b , c,  0,  b,  c},
            { 0,  c,  d,  0, -c,  e},
            {-a,  0,  0,  a,  0,  0},
            { 0, -b, -c,  0,  b, -c},
            { 0,  c,  e,  0, -c,  d},
        };
        RealMatrix localElementStiffnessMatrix = MatrixUtils.createRealMatrix(matrixData);
        return localElementStiffnessMatrix;
    }

    /**
     * Assembles a transformation matrix for the element based on the node coords.
     * @return
     */
    private RealMatrix assembleElementTransformationMatrix(){
        double dx = beam.getEndNode().getxCoordinate() - beam.getStartNode().getxCoordinate();
        double dy = beam.getEndNode().getyCoordinate() - beam.getStartNode().getyCoordinate();

        double c = dx/beam.getLength();
        double s = dy/beam.getLength();

        double[][] matrixData = {
            { c,  s , 0,  0,  0,  0},
            {-s,  c , 0,  0,  0,  0},
            { 0,  0 , 1,  0,  0,  0},
            { 0,  0 , 0,  c,  s,  0},
            { 0,  0 , 0, -s,  c,  0},
            { 0,  0 , 0,  0,  0,  1}
        };

        RealMatrix elementTransformationMatrix = MatrixUtils.createRealMatrix(matrixData);
        return elementTransformationMatrix;
    }

    /**
     * Transform local element stiffness matrix to global coordinates with transformation matrix.
     * @return
     */
    private RealMatrix transformLocalToGlobal(){
        RealMatrix kelem = this.localElementStiffnessMatrix;
        RealMatrix R = this.elementTransformationMatrix;
        RealMatrix Rt = this.elementTransformationMatrix.transpose();

        RealMatrix globalElementStiffnessMatrix = Rt.multiply(kelem).multiply(R);
        return globalElementStiffnessMatrix;
    }

    @Override
    public String toString(){
        return this.globalElementStiffnessMatrix.toString();
    }

  public RealMatrix getGlobalElementStiffnessMatrix() {
    return globalElementStiffnessMatrix;
  }
}
