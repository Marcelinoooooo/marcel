//package Engine;
//
//import org.joml.Vector3f;
//import org.joml.Vector4f;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class spheres extends Circle {
//    float radiusZ;
//    int stackCount;
//    int sectorCount;
//    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ,
//                  int sectorCount, int stackCount, int option) {
//        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
//        this.radiusZ = radiusZ;
//        this.stackCount = stackCount;
//        this.sectorCount = sectorCount;
////        createBox();
////        createSphere();
////createSphere();
//        if(option==1){
//            createSphere();
//        }else if(option==2){
//            createCylinder();
//        }else if(option==3){
//            createElipsoid();
//        }else if(option==4){
//            createEllipticParaboloid();
//        }else if(option==5){
//            createHalfElipsoid();
//        }
//        setupVAOVBO();
//    }
//    public void createBox(){
//        Vector3f temp = new Vector3f();
//        ArrayList<Vector3f> tempVertices = new ArrayList<>();
//        //TITIK 1
//        temp.x = centerPoint.get(0) - radiusX / 2.0f;
//        temp.y = centerPoint.get(1) + radiusY / 2.0f;
//        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 2
//        temp.x = centerPoint.get(0) + radiusX / 2.0f;
//        temp.y = centerPoint.get(1) + radiusY / 2.0f;
//        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 3
//        temp.x = centerPoint.get(0) + radiusX / 2.0f;
//        temp.y = centerPoint.get(1) - radiusY / 2.0f;
//        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 4
//        temp.x = centerPoint.get(0) - radiusX / 2.0f;
//        temp.y = centerPoint.get(1) - radiusY / 2.0f;
//        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 5
//        temp.x = centerPoint.get(0) - radiusX / 2.0f;
//        temp.y = centerPoint.get(1) + radiusY / 2.0f;
//        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 6
//        temp.x = centerPoint.get(0) + radiusX / 2.0f;
//        temp.y = centerPoint.get(1) + radiusY / 2.0f;
//        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 7
//        temp.x = centerPoint.get(0) + radiusX / 2.0f;
//        temp.y = centerPoint.get(1) - radiusY / 2.0f;
//        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//        //TITIK 8
//        temp.x = centerPoint.get(0) - radiusX / 2.0f;
//        temp.y = centerPoint.get(1) - radiusY / 2.0f;
//        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
//        tempVertices.add(temp);
//        temp = new Vector3f();
//
//        vertices.clear();
//        //kotak yg sisi belakang
//        vertices.add(tempVertices.get(0));
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(2));
//        vertices.add(tempVertices.get(3));
//        //kotak yg sisi depan
//        vertices.add(tempVertices.get(4));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(6));
//        vertices.add(tempVertices.get(7));
//        //kotak yg sisi kiri
//        vertices.add(tempVertices.get(0));
//        vertices.add(tempVertices.get(4));
//        vertices.add(tempVertices.get(7));
//        vertices.add(tempVertices.get(3));
//        //kotak yg sisi kanan
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(6));
//        vertices.add(tempVertices.get(2));
//        //kotak yg sisi atas
//        vertices.add(tempVertices.get(0));
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(4));
//        //kotak yg sisi bawah
//        vertices.add(tempVertices.get(3));
//        vertices.add(tempVertices.get(2));
//        vertices.add(tempVertices.get(7));
//        vertices.add(tempVertices.get(6));
//    }
//    public void createElipsoid(){
//        ArrayList<Vector3f>temp=new ArrayList<>();
//        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
//            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
//                float x = 0.05f * (float)(Math.cos(v) * Math.cos(u));
//                float y = 0.2f * (float)(Math.cos(v) * Math.sin(u));
//                float z = 0.0f * (float)(Math.sin(v));
//                temp.add(new Vector3f(x,y,z));
//            }
//        }
//        vertices=temp;
//    }
//    public void createHalfElipsoid(){
//        ArrayList<Vector3f>temp=new ArrayList<>();
//        for(double v = 0; v<= Math.PI/2; v+=Math.PI/60){
//            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
//                float x = 0.05f * (float)(Math.cos(v) * Math.cos(u));
//                float y = 0.2f * (float)(Math.cos(v) * Math.sin(u));
//                float z = 0.f * (float)(Math.sin(v));
//                temp.add(new Vector3f(x,y,z));
//            }
//        }
//        vertices=temp;
//    }
//
//
//    public void createCylinder() {
//        ArrayList<Vector3f> temp = new ArrayList<>();
//        float radius = 0.09f;
//        float height = 0.7f;
//        int slices = 600;
//        for (int i = 0; i <= slices; i++) {
//            double angle = 2.0 * Math.PI / slices * i;
//            float x = radius * (float) Math.cos(angle);
//            float y = height / 2;
//            float z = radius * (float) Math.sin(angle);
//            temp.add(new Vector3f(x, y, z));
//            temp.add(new Vector3f(x, -y, z));
//        }
//        vertices = temp;
//    }
//    public void CreateElipticCone(){
//        vertices.clear();
//        ArrayList<Vector3f> temp = new ArrayList<>();
//
//        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
//            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
//                float x = radiusX * (float)(v * Math.cos(u));
//                float y = radiusY * (float)(v * Math.sin(u));
//                float z = radiusZ * (float)(v);
//                temp.add(new Vector3f(x,z,y));
//            }
//        }
//        vertices = temp;
//    }
//    public void createEllipticParaboloid(){
//        vertices.clear();
//        ArrayList<Vector3f> temp = new ArrayList<>();
////
//        for(double v=0 ;v<=Math.PI/4;v+=Math.PI/600){
//            for(double u=-Math.PI;u<=Math.PI;u+=Math.PI/600){
//                float x = 0.2f * (float) (v*Math.cos(u));
//                float y=0.1f * (float) (v * Math.sin(u));
//                float z=(float) (v*v);
//                temp.add(new Vector3f(z,x,y));
//            }
//        }
//        System.out.println(temp.size());
//        vertices = temp;
//
//    }
//    //    public void draw(){
////        drawSetup();
////        glLineWidth(2); //ketebalan garis
////        glPointSize(2); //besar kecil vertex
////        glDrawArrays(GL_LINE_STRIP,
////                0,
////                vertices.size());
////    }
//    public void createSphere(){
//        float pi = (float)Math.PI;
//
//        float sectorStep = 2 * (float)Math.PI / sectorCount;
//        float stackStep = (float)Math.PI / stackCount;
//        float sectorAngle, StackAngle, x, y, z;
//
//        for (int i = 0; i <= stackCount; ++i)
//        {
//            StackAngle = pi / 2 - i * stackStep;
//            x = radiusX * (float)Math.cos(StackAngle);
//            y = radiusY * (float)Math.cos(StackAngle);
//            z = radiusZ * (float)Math.sin(StackAngle);
//
//            for (int j = 0; j <= sectorCount; ++j)
//            {
//                sectorAngle = j * sectorStep;
//                Vector3f temp_vector = new Vector3f();
//                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
//                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
//                temp_vector.z = centerPoint.get(2) + z;
//                vertices.add(temp_vector);
//            }
//        }
//    }
//}
