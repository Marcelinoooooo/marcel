package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;

public class Sphere extends Circle{
    float radiusZ;
    // vertical spaces in the sphere
    int stackCount;
    // horizontal spaces in the sphere
    int sectorCount;

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color,
                  float radiusX, float radiusY, Vector3f center, float radiusZ, int sectorCount, int stackCount, int shapes) {
        super(shaderModuleDataList, vertices, color, radiusX, radiusY, center);
        this.radiusZ = radiusZ;
        this.sectorCount = sectorCount;
        this.stackCount = stackCount;

        if (shapes == 1){
            CreateSphere();
        } else if (shapes == 2){
            CreateElipsoid();
        } else if (shapes == 3){
            CreateHyperoloid1();
        } else if (shapes == 4){
            CreateHyperoloid2();
        } else if (shapes == 5){
            CreateElipticCone();
        } else if (shapes == 6){
          CreateElipticParaboloid();
        } else if (shapes == 7){
            CreateHyperboloidParaboloid();
        } else if (shapes == 8){
            CreateCylinder();
        } else if (shapes == 9){
            createellipsoid();
        } else if (shapes == 10){
            createelipticparaboloid();
        } else if (shapes == 11){
            createCylinder();
        }else if(shapes==12){
            CreateEllipticParaboloid();
        }else if(shapes==13){
            CreateBox();
        }

        setupVAOVBO();
    }

    public void CreateBox(){
        vertices.clear();
        Vector3f box = new Vector3f();
        ArrayList<Vector3f> boxVertices = new ArrayList<>();
        // top left back 0
        box.x = center.x - radiusX / 2;
        box.y = center.y + radiusY / 2;
        box.z = center.z - radiusZ / 2;
        boxVertices.add(box);
        // bot left back 1
        box = new Vector3f();
        box.x = center.x - radiusX / 2;
        box.y = center.y - radiusY / 2;
        box.z = center.z - radiusZ / 2;
        boxVertices.add(box);
        // top left front 2
        box = new Vector3f();
        box.x = center.x - radiusX / 2;
        box.y = center.y + radiusY / 2;
        box.z = center.z + radiusZ / 2;
        boxVertices.add(box);
        // bot left front 3
        box = new Vector3f();
        box.x = center.x - radiusX / 2;
        box.y = center.y - radiusY / 2;
        box.z = center.z + radiusZ / 2;
        boxVertices.add(box);
        // top right back 4
        box = new Vector3f();
        box.x = center.x + radiusX / 2;
        box.y = center.y + radiusY / 2;
        box.z = center.z - radiusZ / 2;
        boxVertices.add(box);
        // bot right back 5
        box = new Vector3f();
        box.x = center.x + radiusX / 2;
        box.y = center.y - radiusY / 2;
        box.z = center.z - radiusZ / 2;
        boxVertices.add(box);
        // top right front 6
        box = new Vector3f();
        box.x = center.x + radiusX / 2;
        box.y = center.y + radiusY / 2;
        box.z = center.z + radiusZ / 2;
        boxVertices.add(box);
        // bot right front 7
        box = new Vector3f();
        box.x = center.x + radiusX / 2;
        box.y = center.y - radiusY / 2;
        box.z = center.z + radiusZ / 2;
        boxVertices.add(box);

        // back square
        vertices.add(boxVertices.get(0));
        vertices.add(boxVertices.get(1));
        vertices.add(boxVertices.get(5));

        vertices.add(boxVertices.get(5));
        vertices.add(boxVertices.get(4));
        vertices.add(boxVertices.get(0));

        // front square
        vertices.add(boxVertices.get(2));
        vertices.add(boxVertices.get(3));
        vertices.add(boxVertices.get(7));

        vertices.add(boxVertices.get(7));
        vertices.add(boxVertices.get(6));
        vertices.add(boxVertices.get(2));

        // left square
        vertices.add(boxVertices.get(0));
        vertices.add(boxVertices.get(1));
        vertices.add(boxVertices.get(3));

        vertices.add(boxVertices.get(3));
        vertices.add(boxVertices.get(2));
        vertices.add(boxVertices.get(0));

        // right square
        vertices.add(boxVertices.get(4));
        vertices.add(boxVertices.get(5));
        vertices.add(boxVertices.get(7));

        vertices.add(boxVertices.get(7));
        vertices.add(boxVertices.get(6));
        vertices.add(boxVertices.get(4));

        // top square
        vertices.add(boxVertices.get(0));
        vertices.add(boxVertices.get(4));
        vertices.add(boxVertices.get(6));

        vertices.add(boxVertices.get(6));
        vertices.add(boxVertices.get(2));
        vertices.add(boxVertices.get(0));

        // bot square
        vertices.add(boxVertices.get(1));
        vertices.add(boxVertices.get(5));
        vertices.add(boxVertices.get(7));

        vertices.add(boxVertices.get(7));
        vertices.add(boxVertices.get(3));
        vertices.add(boxVertices.get(1));

    }

    public void CreateSphere(){
        vertices.clear();
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = center.x + x * (float)Math.cos(sectorAngle);
                temp_vector.y = center.y + y * (float)Math.sin(sectorAngle);
                temp_vector.z = center.z + z;
                vertices.add(temp_vector);
            }
        }
    }
    public void CreateElipticParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(v * Math.cos(u));
                float y = radiusY * (float)(v * Math.sin(u));
                float z = radiusZ * (float)(Math.pow(v,2));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }
    public void CreateEllipticParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
//
        for(double v=0 ;v<=Math.PI/4;v+=Math.PI/600){
            for(double u=-Math.PI;u<=Math.PI;u+=Math.PI/600){
                float x = 0.2f * (float) (v*Math.cos(u));
                float y=0.1f * (float) (v * Math.sin(u));
                float z=(float) (v*v);
                temp.add(new Vector3f(z,x,y));
            }
        }
        System.out.println(temp.size());
        vertices = temp;

    }

    public void CreateElipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(Math.cos(v) * Math.cos(u));
                float y = radiusY * (float)(Math.cos(v) * Math.sin(u));
                float z = radiusZ * (float)(Math.sin(v));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;

    }

    public void CreateHyperoloid1(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float z = radiusX * (float)(1/Math.cos(v) * Math.cos(u));
                float y = radiusY * (float)(1/Math.cos(v) * Math.sin(u));
                float x = radiusZ * (float)(Math.tan(v));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }

    public void CreateHyperoloid2(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/60){
                float x = radiusX * (float)(Math.tan(v) * Math.cos(u));
                float y = radiusY * (float)(Math.tan(v) * Math.sin(u));
                float z = radiusZ * (float)(1.0/Math.cos(v));
                temp.add(new Vector3f(x,z,y));
            }

            for(double u = Math.PI/2; u<= 3 * Math.PI / 2; u+=Math.PI/60){
                float x = radiusX * (float)(Math.tan(v) * Math.cos(u));
                float y = radiusY * (float)(Math.tan(v) * Math.sin(u));
                float z = radiusZ * (float)(1.0/Math.cos(v));
                temp.add(new Vector3f(x,-z,y));
            }
        }
        vertices = temp;
    }

    public void CreateElipticCone(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(v * Math.cos(u));
                float y = radiusY * (float)(v * Math.sin(u));
                float z = radiusZ * (float)(v);
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }

    public void createEllipticParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
//
        for(double v=0 ;v<=Math.PI/4;v+=Math.PI/600){
            for(double u=-Math.PI;u<=Math.PI;u+=Math.PI/600){
                float x = 0.2f * (float) (v*Math.cos(u));
                float y=0.1f * (float) (v * Math.sin(u));
                float z=(float) (v*v);
                temp.add(new Vector3f(z,x,y));
            }
        }
        System.out.println(temp.size());
        vertices = temp;

    }

    public void CreateHyperboloidParaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= 1; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(v * Math.tan(u));
                float y = radiusY * (float)(v * (1.0f/Math.cos(u)));
                float z = radiusZ * (float)(Math.pow(v,2));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;
    }

    public void CreateCylinder() {
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();
        int slices = 600;
        for (int i = 0; i <= slices; i++) {
            double angle = 2.0 * Math.PI / slices * i;
            float x = radiusX * (float) Math.cos(angle);
            float y = radiusY / 2;
            float z = radiusZ * (float) Math.sin(angle);
            temp.add(new Vector3f(x, y, z));
            temp.add(new Vector3f(x, -y, z));
        }
        vertices = temp;
    }

    // for boo
    public void createelipticparaboloid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = Math.PI/8 ; v <= Math.PI; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(v * Math.cos(u));
                float y = 0.5f * (float)(v * Math.sin(u));
                float z =(float)(Math.pow(v,2));
                temp.add(new Vector3f(x,z,y));
            }
        }
        vertices = temp;

    }

    public void createellipsoid(){
        vertices.clear();
        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(Math.cos(v) * Math.cos(u));
                float y = 0.5f * (float)(Math.cos(v) * Math.sin(u));
                float z = 0.5f * (float)(Math.sin(v));
                temp.add(new Vector3f(x,y,z));
            }
        }
        vertices = temp;

    }

    public void createCylinder() {
        ArrayList<Vector3f> temp = new ArrayList<>();
        float radius = 0.09f;
        float height = 0.7f;
        int slices = 600;
        for (int i = 0; i <= slices; i++) {
            double angle = 2.0 * Math.PI / slices * i;
            float x = radius * (float) Math.cos(angle);
            float y = height / 2;
            float z = radius * (float) Math.sin(angle);
            temp.add(new Vector3f(x, y, z));
            temp.add(new Vector3f(x, -y, z));
        }
        vertices = temp;
    }

//    public void draw(){
//        //System.out.println("hi");
//        drawSetup();
//        glLineWidth(1); //ketebalan garis
//        glPointSize(1); //besar kecil vertex
//        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
//    }

    public void drawIndices(Camera camera, Projection projection){
        drawSetup(camera, projection);
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
    }
}
