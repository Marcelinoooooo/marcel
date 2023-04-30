package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.*;

import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPointSize;
import static org.lwjgl.opengl.GL11C.*;

public class Circle extends Objects {

    float radiusX;
    float radiusY;
    float x;
    float y;

    List<Vector3f> circle;

    Vector3f center;

    // the vertices should be the circle's line
    public Circle(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color,
                  float radiusX, float radiusY, Vector3f center) {
        super(shaderModuleDataList, vertices, color);
        // set the vertices to be the circle's line
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.center = center;
        CreateCircle2D();
        this.vertices = circle;
        setupVAOVBO();
    }

    public void CreateCircle2D(){
        // initialize the circle
        circle = new ArrayList<>();
        circle.add(new Vector3f(center.x, center.y, center.z));

        // use this variables in the loop to make shapes according to the variable's name
        float circleLine = 0.01f;
        float triangleLine = 120f;
        float squareLine = 90f;

        // for a triangle like /_\ use i = 90; i <= 450;
        // for a square like |=| use i = 45; i <= 405;
        // for a normal circle use i = 0; i <= 360;
        for (float i = 0; i <= 360; i += circleLine){
            double rad = Math.toRadians(i);
            // calculate a point in the circle's line
            x = (float)(radiusX * Math.cos(rad) + center.x);
            y = (float)(radiusY * Math.sin(rad) + center.y);

            circle.add(new Vector3f(x, y, 0.0f));
        }
    }

//    public void draw(){
//        drawSetup();
//        glLineWidth(10);
//        glPointSize(10);
//        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
//    }
}