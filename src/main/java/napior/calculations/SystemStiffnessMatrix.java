package napior.calculations;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;

public class SystemStiffnessMatrix {

  private ArrayList<Beam> beams;
  private ArrayList<Node> nodes;
  private RealMatrix systemStiffnessMatrix;

  public SystemStiffnessMatrix(ArrayList<Beam> beams, ArrayList<Node> nodes) {
    this.beams = beams;
    this.nodes = nodes;
    this.createSystemStiffnessMatrix();
  }

  private void createSystemStiffnessMatrix(){
    int nodeQuantity = this.nodes.size();
    int degreesOfFreedom = BeamCalculation.NODE_DEGREES_OF_FREEDOM * nodeQuantity;
    double[][] systemMatrixData = new double[degreesOfFreedom][degreesOfFreedom];
    this.systemStiffnessMatrix = MatrixUtils.createRealMatrix(systemMatrixData);

  }

  public RealMatrix getSystemStiffnessMatrix() {
    return systemStiffnessMatrix;
  }
}
