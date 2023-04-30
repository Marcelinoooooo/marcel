import Engine.*;
import Engine.Objects;
import org.joml.*;
import org.lwjgl.opengl.GL;

import java.lang.Math;
import java.util.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window = new Window(750, 750, "Hello World");

    //Matrix4f model;
//
//    ArrayList<Objects> objects = new ArrayList<>();
//    ArrayList<Objects> objectsRectangle = new ArrayList<>();
//    ArrayList<Objects> objectsCircle = new ArrayList<>();
//    ArrayList<Objects> objectsStar = new ArrayList<>();
//    ArrayList<Objects> objectsControl = new ArrayList<>();
//    ArrayList<Objects> objectsMovable = new ArrayList<>();
    ArrayList<Objects> objectsGoombaCurves = new ArrayList<>();
    ArrayList<Objects> objectsGoomba = new ArrayList<>();
    ArrayList<Objects> objectsBoo = new ArrayList<>();

    ArrayList<Objects> Background = new ArrayList<>();

    ArrayList<Objects>BerzierBackground=new ArrayList<>();

     ArrayList<Objects>bob_omb=new ArrayList<>();
    ArrayList<Objects>bob_ombberzier=new ArrayList<>();
    ArrayList<Objects> objectsBooCurves = new ArrayList<>();

    boolean inMovingState = false;
    int currentRectangle = 0;

    Camera camera = new Camera();
    Projection projection = new Projection(window.getWidth(), window.getHeight());

    ArrayList<Vector3f> offsets = new ArrayList<>();

    // determines which foot moved first
    float feetMov = 1;
    // used to clamp foot movement
    float feetMovDeg = 0;

    // adjust animation directionality
    float forwardDegX = 0f;
    float forwardDegZ = 90f;
    float xDegreeOffset = 1f;
    float zDegreeOffset = -1f;
    float forwardX = 60;
    float forwardZ = 60;



    // used in boo animation
    float limiter = 20;
    float counter = 0;

    float anchor = 1;

    float anchor2=1;
    float counter2=0;



    public void run() {

        init();
        loop();

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        //model = new Matrix4f().identity();
        window.init();
        GL.createCapabilities();
        camera.setPosition(0.0f, 0.0f, 5.0f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.0f));

//        RICKY JOHANNES CODE SHAPE
        // Head
        objectsGoomba.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.722f, 0.424f, 0.275f, 1.0f),
                0.38f,
                0.38f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.275f,
                36,
                18,
                6
        ));
        objectsGoomba.get(0).scaleObject(1, 1, 0.65f);
        objectsGoomba.get(0).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).translateObject(0.0f, 0.0f, 0.0125f);
        offsets.add(new Vector3f(0.0f, 0.0f, 0.0f));

        // Head Fat
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.722f, 0.424f, 0.275f, 1.0f),
                0.4f,
                0.4f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.3f,
                36,
                18,
                2
        ));
        objectsGoomba.get(0).getChildObject().get(0).scaleObject(1.55f, 0.70f, 0.95f);
        objectsGoomba.get(0).getChildObject().get(0).translateObject(0.0f, -0.625f, 0.0f);

        // Body
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.804f, 0.686f, 0.541f, 1.0f),
                0.4f,
                0.6f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.4f,
                36,
                18,
                8
        ));
        objectsGoomba.get(0).getChildObject().get(1).scaleObject(0.65f, 1f, 0.65f);
        objectsGoomba.get(0).getChildObject().get(1).translateObject(0.0f, -0.75f, 0.0f);

        // left foot
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.392f, 0.271f, 0.177f, 1.0f),
                0.125f,
                0.125f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                6
        ));
        objectsGoomba.get(0).getChildObject().get(2).scaleObject(1.0f, 1.0f, 1.5f);
        objectsGoomba.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(2).translateObject(0.25f, -1.0f, 0.0f);

        // right foot
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.392f, 0.271f, 0.177f, 1.0f),
                0.125f,
                0.125f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                6
        ));
        objectsGoomba.get(0).getChildObject().get(3).scaleObject(1.0f, 1.0f, 1.5f);
        objectsGoomba.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(3).translateObject(-0.25f, -1.0f, 0.0f);

        // left eye
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(255f, 255f, 255f, 1.0f),
                0.1f,
                0.1f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                2
        ));
        objectsGoomba.get(0).getChildObject().get(4).scaleObject(1.575f, 1.0f, 0.01f);
        objectsGoomba.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(80), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(22.5), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(4).translateObject(-0.135f, -0.3f, 0.2675f);

        // right eye
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(255f, 255f, 255f, 1.0f),
                0.1f,
                0.1f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                2
        ));
        objectsGoomba.get(0).getChildObject().get(5).scaleObject(1.575f, 1.0f, 0.01f);
        objectsGoomba.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(100), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(22.5), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(5).translateObject(0.135f, -0.3f, 0.2675f);

        // Left pupils
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                0.1f,
                0.1f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                2
        ));
        objectsGoomba.get(0).getChildObject().get(6).scaleObject(0.65f, 0.35f, 0.01f);
        objectsGoomba.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(75), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(22.5), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(6).translateObject(-0.1f, -0.3f, 0.27f);

        // right pupils
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                0.1f,
                0.1f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                2
        ));
        objectsGoomba.get(0).getChildObject().get(7).scaleObject(0.65f, 0.35f, 0.01f);
        objectsGoomba.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(95), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(22.5), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(7).translateObject(0.1f, -0.3f, 0.27f);

        // left pupil's reflection
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(255f, 255f, 255f, 1.0f),
                0.1f,
                0.1f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                2
        ));
        objectsGoomba.get(0).getChildObject().get(8).scaleObject(0.18f, 0.1f, 0.01f);
        objectsGoomba.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(85), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(22.5), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(8).translateObject(-0.085f, -0.265f, 0.272f);

        // right pupil's reflection
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(255f, 255f, 255f, 1.0f),
                0.1f,
                0.1f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.1f,
                36,
                18,
                2
        ));
        objectsGoomba.get(0).getChildObject().get(9).scaleObject(0.18f, 0.1f, 0.01f);
        objectsGoomba.get(0).getChildObject().get(9).rotateObject((float) Math.toRadians(105), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(9).rotateObject((float) Math.toRadians(20), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(9).translateObject(0.085f, -0.265f, 0.272f);

        // Left Tooth
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(255f, 255f, 255f, 1.0f),
                0.035f,
                0.035f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.05f,
                36,
                18,
                6
        ));
        objectsGoomba.get(0).getChildObject().get(10).scaleObject(1.2f, 1.2f, 0.25f);
        objectsGoomba.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(20), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(10).translateObject(-0.305f, -0.465f, 0.3f);

        // Right Tooth
        objectsGoomba.get(0).addChild(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(255f, 255f, 255f, 1.0f),
                0.035f,
                0.035f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.05f,
                36,
                18,
                6
        ));
        objectsGoomba.get(0).getChildObject().get(11).scaleObject(1.2f, 1.2f, 0.25f);
        objectsGoomba.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 1.0f);
        objectsGoomba.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(22.5), -1.0f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(11).translateObject(0.305f, -0.465f, 0.3f);

        // Mouth
        objectsGoombaCurves.add(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.255f,0.075f,0.0275f,1.0f)
        ));
        objectsGoombaCurves.get(0).addVertices(new Vector3f(0.0f, 0.0f, 0.0f));
        objectsGoombaCurves.get(0).addVertices(new Vector3f(0.4f, 0.125f, 0.0f));
        objectsGoombaCurves.get(0).addVertices(new Vector3f(0.8f, 0.0f, 0.0f));
        objectsGoombaCurves.get(0).translateObject(-0.4f, -0.615f, 0.365f);

        // Left Eyebrow
        objectsGoombaCurves.get(0).addChild(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.255f,0.075f,0.0275f,1.0f)
        ));
        objectsGoombaCurves.get(0).getChildObject().get(0).addVertices(new Vector3f(0.0f, 0.65f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(0).addVertices(new Vector3f(0.5f, 0.95f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(0).addVertices(new Vector3f(0.5f, 0.0f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(0).addVertices(new Vector3f(1.0f, 0.0f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(0).scaleObject(0.35f, 0.35f, 1.0f);
        objectsGoombaCurves.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(15), -1.0f, 0.0f, 0.0f);
        objectsGoombaCurves.get(0).getChildObject().get(0).translateObject(-0.385f, -0.185f, 0.25f);

        // Right Eyebrow
        objectsGoombaCurves.get(0).addChild(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.255f,0.075f,0.0275f,1.0f)
        ));
        objectsGoombaCurves.get(0).getChildObject().get(1).addVertices(new Vector3f(1.0f, 0.65f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(1).addVertices(new Vector3f(0.5f, 0.95f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(1).addVertices(new Vector3f(0.5f, 0.0f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(1).addVertices(new Vector3f(0.0f, 0.0f, 0.15f));
        objectsGoombaCurves.get(0).getChildObject().get(1).scaleObject(0.35f, 0.35f, 1.0f);
        objectsGoombaCurves.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(15), -1.0f, 0.0f, 0.0f);
        objectsGoombaCurves.get(0).getChildObject().get(1).translateObject(0.055f, -0.185f, 0.25f);

        // Middle Eyebrow
        objectsGoombaCurves.get(0).addChild(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.255f,0.075f,0.0275f,1.0f)
        ));
        objectsGoombaCurves.get(0).getChildObject().get(2).addVertices(new Vector3f(0.0f, 0.5f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(2).addVertices(new Vector3f(0.125f, 0.15f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(2).addVertices(new Vector3f(0.25f, 0.5f, 0.0f));
        objectsGoombaCurves.get(0).getChildObject().get(2).scaleObject(0.45f, 0.5f, 1.0f);
        objectsGoombaCurves.get(0).getChildObject().get(2).translateObject(-0.0445f, -0.385f, 0.25f);

        objectsGoomba.get(0).addChild(objectsGoombaCurves.get(0));
        //objectsGoomba.get(0).getChildObject().get(12).translateObject(-2f, 0.0f, 0.0f);
        objectsGoomba.get(0).getChildObject().get(12).setExcludeDraw(true);
// -------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------------
// DANIEL CHANDRA BOO OBJECT SHAPE
        // Badan
        objectsBoo.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.5f,
                0.5f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.5f,
                36,
                18,
                9
        ));

        objectsBoo.get(0).scaleObject(0.2f,0.2f,0.2f);
        objectsBoo.get(0).translateObject(0.0f,0.0f,0.0f);

        //mata kanan
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                0.5f,
                0.5f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.5f,
                36,
                18,
                9
        ));

        //mata kiri
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                0.5f,
                0.5f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.5f,
                36,
                18,
                9
        ));

        //mulut
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                0.5f,
                0.5f,
                new Vector3f(0.0f,0.0f,0.0f),
                1f,
                36,
                18,
                10
        ));

        //ujung lidah
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,0.0f,0.0f,1.0f),
                0.5f,
                0.5f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.5f,
                36,
                18,
                9


        ));

        //sirip kanan
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                10


        ));

        //sirip kiri
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                10


        ));




        //gigi taring kiri
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                10


        ));
        //gigi taring kanan
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                10


        ));
        //gigi kiri
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                10


        ));
        //gigi kanan
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                10


        ));

        //ekor
        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                10


        ));

        //batang lidah

        objectsBoo.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,0.0f,0.0f,1.0f),
                0.1f,
                0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.1f,
                36,
                18,
                11

        ));

        // alis kanan
        objectsBooCurves.add(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f)
        ));

        objectsBooCurves.get(0).addVertices(new Vector3f(-0.02f, 0.0f, 0.0f));
        objectsBooCurves.get(0).addVertices(new Vector3f(0.0f, -0.02f, 0.0f));
        objectsBooCurves.get(0).addVertices(new Vector3f(0.02f, 0.0f, 0.0f));

        objectsBooCurves.get(0).translateObject(-0.02f, -0.05f, 0.09f);

        // alis kiri
        objectsBooCurves.get(0).addChild(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f)
        ));

        objectsBooCurves.get(0).getChildObject().get(0).addVertices(new Vector3f(-0.02f, 0.0f, 0.0f));
        objectsBooCurves.get(0).getChildObject().get(0).addVertices(new Vector3f(0.0f, -0.02f, 0.0f));
        objectsBooCurves.get(0).getChildObject().get(0).addVertices(new Vector3f(0.02f, 0.0f, 0.0f));

        objectsBooCurves.get(0).getChildObject().get(0).translateObject(0.02f, -0.05f, 0.09f);

        objectsBoo.get(0).getChildObject().get(0).translateObject(1.f,1.0f,3.5f);
        objectsBoo.get(0).getChildObject().get(0).scaleObject(0.025f,0.025f,0.025f);

        objectsBoo.get(0).getChildObject().get(1).translateObject(-1.f,1.0f,3.5f);
        objectsBoo.get(0).getChildObject().get(1).scaleObject(0.025f,0.025f,0.025f);

        objectsBoo.get(0).getChildObject().get(2).translateObject(0.f,-12.0f,2.75f);
        objectsBoo.get(0).getChildObject().get(2).scaleObject(0.025f,0.005f,0.0245f);

        objectsBoo.get(0).getChildObject().get(3).translateObject(0.f,-2.8f,3.8f);
        objectsBoo.get(0).getChildObject().get(3).scaleObject(0.028f,0.020f,0.028f);

        objectsBoo.get(0).getChildObject().get(4).translateObject(-0.0f,-17.0f,1.0f);
        objectsBoo.get(0).getChildObject().get(4).scaleObject(0.03f,0.01f,0.02f);
        objectsBoo.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90), 0.0f, 0.0f, 1.0f);
        objectsBoo.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(-10), 0.0f, 1.0f, 0.0f);
        objectsBoo.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(90), 1.0f, 0.0f, 0.0f);

        objectsBoo.get(0).getChildObject().get(5).translateObject(-0.0f,-17.0f,1.0f);
        objectsBoo.get(0).getChildObject().get(5).scaleObject(0.03f,0.01f,0.02f);
        objectsBoo.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(270), 0.0f, 0.0f, 1.0f);
        objectsBoo.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(10), 0.0f, 1.0f, 0.0f);
        objectsBoo.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(90), 1.0f, 0.0f, 0.0f);

        objectsBoo.get(0).getChildObject().get(6).translateObject(-3.5f,-14.2f,19.4f);
        objectsBoo.get(0).getChildObject().get(6).scaleObject(0.005f,0.0022f,0.005f);

        objectsBoo.get(0).getChildObject().get(7).translateObject(3.5f,-14.2f,19.4f);
        objectsBoo.get(0).getChildObject().get(7).scaleObject(0.005f,0.0022f,0.005f);

        objectsBoo.get(0).getChildObject().get(8).translateObject(-1.1f,-16.4f,20.0f);
        objectsBoo.get(0).getChildObject().get(8).scaleObject(0.005f,0.0015f,0.005f);

        objectsBoo.get(0).getChildObject().get(9).translateObject(1.1f,-16.4f,20.0f);
        objectsBoo.get(0).getChildObject().get(9).scaleObject(0.005f,0.0015f,0.005f);

        objectsBoo.get(0).getChildObject().get(10).translateObject(-0.0f,-14.0f,0.6f);
        objectsBoo.get(0).getChildObject().get(10).scaleObject(0.04f,0.012f,0.035f);
        objectsBoo.get(0).getChildObject().get(10).rotateObject((float) Math.toRadians(75), 1.0f, 0.0f, 0.0f);

        objectsBoo.get(0).getChildObject().get(11).translateObject(0.0f,0.8f,0.25f);
        objectsBoo.get(0).getChildObject().get(11).scaleObject(0.2f,0.1f,0.1f);
        objectsBoo.get(0).getChildObject().get(11).rotateObject((float) Math.toRadians(105), 1.0f, 0.0f, 0.0f);

        objectsBoo.get(0).translateObject(0.0f,-0.1f,0.0f);

        objectsBoo.get(0).scaleObject(5.0f, 5.0f, 5.0f);
        objectsBooCurves.get(0).scaleObject(5.0f, 5.0f, 5.0f);

        objectsBoo.get(0).addChild(objectsBooCurves.get(0));
        objectsBoo.get(0).getChildObject().get(12).setExcludeDraw(true);

        objectsGoomba.get(0).translateObject(2.0f, 0.0f, 0.0f);
        //objectsGoombaCurves.get(0).translateObject(2.0f, 0.0f, 0.0f);

//        ///////////////////////////////////////////////////////////////////////////////////////////////////
//        Marcel - C14210108
        bob_omb.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,0.0f),
                0.8f,
                0.8f,
                new Vector3f(0.0f,0.0f,0.0f),
                0.6f,
                500,
                500,
                1
        ));
        //                Mata kiri
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.1f,
                        0.1f,
                new Vector3f(0.0f,0.0f,0.0f),
                        0.7f,
                        36,
                        18,
                        2
                )
        );
        bob_omb.get(0).getChildObject().get(0).scaleObject(1.575f, 0.3f, 1.0f);
        bob_omb.get(0).getChildObject().get(0).translateObject(-0.15f, 0.1f, 0.6055f);
// Mata kanan

        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.1f,
                        0.1f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.7f,
                        36,
                        18,
                        2
                )
        );
        bob_omb.get(0).getChildObject().get(1).scaleObject(1.575f, 0.3f, 1.0f);
        bob_omb.get(0).getChildObject().get(1).translateObject(0.3f, 0.1f, 0.6055f);

//        Kaki kiri
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.1f,
                        0.1f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.4f,
                        36,
                        18,
                        2
                )
        );
        bob_omb.get(0).getChildObject().get(2).scaleObject(3.7f,0.4f,0.7f);
        bob_omb.get(0).getChildObject().get(2).translateObject(-0.3f,-0.6f,0.55f);

//        Kaki kanan
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.1f,
                        0.1f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.3f,
                        36,
                        18,
                        2
                )
        );
        bob_omb.get(0).getChildObject().get(3).scaleObject(3.7f,0.7f,0.4f);
        bob_omb.get(0).getChildObject().get(3).translateObject(0.5f,-0.6f,0.1f);

//Kotak ekor
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.1f,
                        0.1f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.2f,
                        36,
                        18,
                        11
                )
        );
        bob_omb.get(0).getChildObject().get(4).scaleObject(0.8f,0.55f,0.0f);
        bob_omb.get(0).getChildObject().get(4).translateObject(-0.8f,0.1f,0.0f);
// Eliptic paraboloid
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.1f,
                        0.1f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.2f,
                        36,
                        18,
                        12
                )
        );
        bob_omb.get(0).getChildObject().get(5).scaleObject(0.8f,1.25f,1.0f);
        bob_omb.get(0).getChildObject().get(5).translateObject(-1.35f,0.1f,0.0f);
//Lingkaran ekor atas
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.2f,
                        0.2f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.2f,
                        36,
                        18,
                        1
                )
        );
        bob_omb.get(0).getChildObject().get(6).scaleObject(0.8f,0.8f,0.8f);
        bob_omb.get(0).getChildObject().get(6).translateObject(-1.1f,0.2f,0.0f);
//Lingkaran ekor bawah
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.2f,
                        0.2f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.2f,
                        36,
                        18,
                        1

        ));
        bob_omb.get(0).getChildObject().get(7).scaleObject(0.8f,0.8f,0.8f);
        bob_omb.get(0).getChildObject().get(7).translateObject(-1.1f,-0.05f,0.0f);
//        lingkaran kecil atas
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f,0.0f,0.0f,0.0f),
                        0.2f,
                        0.2f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.2f,
                        36,
                        18,
                        1
                )
        );
        bob_omb.get(0).getChildObject().get(8).scaleObject(0.2f,0.2f,0.1f);
        bob_omb.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(80), 0.0f, 0.0f, 1.0f);
        bob_omb.get(0).getChildObject().get(8).rotateObject((float) Math.toRadians(22.5), -0.4f, 0.0f, 0.0f);
//bob_omb.get(0).getChildObject().get(8).rotateObject((float)30.0f,0.5f,0.5f,0.0f);
        bob_omb.get(0).getChildObject().get(8).translateObject(-1.03f,0.25f,0.2f);
//        Lingkaran Kecil bawah
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f,0.0f,0.0f,0.0f),
                        0.2f,
                        0.2f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.2f,
                        36,
                        18,
                        1
                )
        );
        bob_omb.get(0).getChildObject().get(9).scaleObject(0.2f,0.2f,0.1f);
        bob_omb.get(0).getChildObject().get(9).translateObject(-1.03f,-0.1f,0.2f);
// sumbu kotak
        bob_omb.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f,1.0f,1.0f,1.0f),
                        0.2f,
                        0.2f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.2f,
                        36,
                        18,
                        11
                )
        );
        bob_omb.get(0).getChildObject().get(10).scaleObject(1.7f,0.3f,0.6f);
        bob_omb.get(0).getChildObject().get(10).translateObject(0.0f,0.9f,0.0f);

//     Berzier
        bob_ombberzier.add(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f)
        ));
        bob_ombberzier.get(0).addVertices(new Vector3f(0.0f, 0.65f, 0.0f));
       bob_ombberzier.get(0).addVertices(new Vector3f(0.5f, 0.95f, 0.0f));
        bob_ombberzier.get(0).addVertices(new Vector3f(0.5f, 0.0f, 0.0f));
        bob_ombberzier.get(0).scaleObject(0.55f, 0.95f, 1.0f);
        bob_ombberzier.get(0).rotateObject((float) Math.toRadians(15), -1.0f, 0.0f, 0.0f);
        bob_ombberzier.get(0).translateObject(-0.185f, 0.785f, 0.05f);
        bob_omb.get(0).getChildObject().add(bob_ombberzier.get(0));
        bob_omb.get(0).getChildObject().get(11).setExcludeDraw(true);


//        bob_omb.get(0).scaleObject(0.8f,0.8f,1.0f);
//bob_omb.get(0).translateObject(-1.5f,-0.4f,0.0f);
//Background 1
        Background.add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f,1.0f,0.0f,0.0f),
                        1.8f,
                        1.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        13
                )
        );
        Background.get(0).scaleObject(35.0f,5.0f,0.000001f);
        Background.get(0).translateObject(0.0f,-6.0f,-10.0f);
//tanah
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.722f, 0.424f, 0.275f, 1.0f),
                        1.8f,
                        1.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        13
                )
        );
        Background.get(0).getChildObject().get(0).scaleObject(35.0f,0.5f,0.000001f);
        Background.get(0).getChildObject().get(0).translateObject(0.0f,-1.2f,-10.0f);

// Ijo tabung
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 1.0f, 0.0f, 0.0f),
                        6.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(1).scaleObject(16.0f,8.5f,0.000001f);
        Background.get(0).getChildObject().get(1).translateObject(-8.3f,1.2f,-10.0f);
//        Tabung ijo atas
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 4.0f, 0.0f, 0.0f),
                        6.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(2).scaleObject(23.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(2).translateObject(-8.2f,4.2f,-10.0f);
//        Castle bawah
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.5f, 0.5f, 1.0f, 1.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(3).scaleObject(50.0f,6.5f,0.000001f);
        Background.get(0).getChildObject().get(3).translateObject(0.3f,1.4f,-10.0f);
//Castle Atas
//        Background.get(0).getChildObject().add(new Sphere(
//                        Arrays.asList(
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                        ),
//                        new ArrayList<>(),
//                        new Vector4f(1.722f, 0.424f, 0.275f, 1.0f),
//                        12.8f,
//                        3.8f,
//                        new Vector3f(0.0f,0.0f,0.0f),
//                        0.1f,
//                        36,
//                        18,
//                        11
//                )
//        );
//        Background.get(0).getChildObject().get(4).scaleObject(30.0f,5.5f,0.000001f);
//        Background.get(0).getChildObject().get(4).translateObject(0.2f,5.4f,-10.0f);
//        Castle Kotak  kecil 1
                Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(4).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(4).translateObject(-3.7f,4.0f,-10.0f);
// Kotak kecil 2
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(5).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(5).translateObject(-1.7f,4.0f,-10.0f);
//        Kotak kecil 3
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(6).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(6).translateObject(0.3f,4.0f,-10.0f);
//        Kotk kecil 4
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(7).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(7).translateObject(2.3f,4.0f,-10.0f);
//        Kotak kecil 5
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(8).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(8).translateObject(4.3f,4.0f,-10.0f);
// castle atas
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.5f, 0.5f, 1.0f, 1.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(9).scaleObject(35.0f,5.5f,0.000001f);
        Background.get(0).getChildObject().get(9).translateObject(0.2f,5.4f,-10.0f);
//        Kotak kecil atas 1
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(10).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(10).translateObject(-2.5f,7.8f,-10.0f);
//        Kotak kecil atas 2
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(11).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(11).translateObject(-0.5f,7.8f,-10.0f);
//        Kotak kecil atas 3
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(12).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(12).translateObject(1.5f,7.8f,-10.0f);
//        Kotak kecil 4
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(13).scaleObject(5.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(13).translateObject(2.9f,7.8f,-10.0f);
// Kotak hitam castle
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(14).scaleObject(10.0f,3.6f,0.000001f);
        Background.get(0).getChildObject().get(14).translateObject(0.1f,0.7f,-9.0f);
//Tanah pembatas item
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        1.8f,
                        0.3f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        13
                )
        );
        Background.get(0).getChildObject().get(15).scaleObject(0.0f,0.0f,0.000001f);
        Background.get(0).getChildObject().get(15).translateObject(0.0f,-0.49f,-7.0f);

//        Ijo tabung kanan
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 1.0f, 0.0f, 0.0f),
                        6.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(16).scaleObject(16.0f,8.5f,0.000001f);
        Background.get(0).getChildObject().get(16).translateObject(8.3f,1.2f,-10.0f);

//        Ijo tabung kanan atas
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 4.0f, 0.0f, 0.0f),
                        6.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(17).scaleObject(23.0f,1.5f,0.000001f);
        Background.get(0).getChildObject().get(17).translateObject(8.4f,4.2f,-10.0f);



bob_omb.get(0).translateObject(-3.0f,0.0f,-3.0f);
objectsBoo.get(0).translateObject(-0.0f,2.0f,0.0f);
//objectsBoo.get(0).scaleObject(2.0f,2.0f,2.0f);
//bob_omb.get(0).scaleObject(0.8f,0.8f,1.0f);
//objectsGoomba.get(0).translateObject(18.0f,0.0f,-6.0f);
// HOUSE FOR GOMBAA
        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                new Vector4f(0.722f, 0.424f, 0.275f, 1.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(18).scaleObject(50.0f,8.5f,0.000001f);
        Background.get(0).getChildObject().get(18).translateObject(20.3f,1.4f,-10.0f);

        Background.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                // color
                new Vector4f(0.722f, 0.424f, 0.275f, 1.0f),
                0.38f,
                0.38f,
                // center
                new Vector3f(0.0f, 0.0f, 0.0f),
                0.275f,
                500,
                500,
                6
        ));
        Background.get(0).getChildObject().get(19).scaleObject(10, 8, 0.0f);
        Background.get(0).getChildObject().get(19).rotateObject((float) Math.toRadians(180), 0.0f, 0.0f, 0.0f);
        Background.get(0).getChildObject().get(19).translateObject(20.5f, 9.0f, -10.0f);

        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        12.8f,
                        3.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        1.1f,
                        36,
                        18,
                        11
                )
        );
        Background.get(0).getChildObject().get(20).scaleObject(10.0f,4.0f,0.000001f);
        Background.get(0).getChildObject().get(20).translateObject(20.1f,0.7f,-9.5f);

//        HOUSE FOR BOB-OMB

        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.0f, 0.0f, 0.0f, 0.0f),
                        0.8f,
                        0.8f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.0f,
                        36,
                        18,
                        1
                )
        );
        Background.get(0).getChildObject().get(21).scaleObject(5.0f,5.0f,-10.3f);
        Background.get(0).getChildObject().get(21).translateObject(-20.0f,2.0f,-10.3f);

        Background.get(0).getChildObject().add(new Sphere(
                        Arrays.asList(
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                        0.5f,
                        0.7f,
                        new Vector3f(0.0f,0.0f,0.0f),
                        0.001f,
                        36,
                        18,
                        13
                )
        );
        Background.get(0).getChildObject().get(22).scaleObject(5.0f,5.0f,-10.4f);
        Background.get(0).getChildObject().get(22).translateObject(-20.0f,1.0f,-10.3f);

        BerzierBackground.add(new Curve(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,0.0f)
        ));
      BerzierBackground.get(0).addVertices(new Vector3f(0.0f, 0.65f, 0.0f));
       BerzierBackground.get(0).addVertices(new Vector3f(0.5f, 0.95f, 0.0f));
        BerzierBackground.get(0).addVertices(new Vector3f(0.5f, 0.0f, 0.0f));
       BerzierBackground.get(0).addVertices(new Vector3f(1.0f, 0.0f, 0.0f));
        BerzierBackground.get(0).scaleObject(5.35f, 5.35f, 1.0f);
       BerzierBackground.get(0).rotateObject((float) Math.toRadians(15), -1.0f, 0.0f, 0.0f);
        BerzierBackground.get(0).translateObject(-25.385f, 5.185f, -10.25f);

    }
    public void input(){
        if (window.isKeyPressed(GLFW_KEY_A)){
            //Background.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            objectsGoomba.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            //objectsGoombaCurves.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            objectsBoo.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            //objectsBooCurves.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            bob_omb.get(0).rotateObject((float)Math.toRadians(1f),0.0f,1.0f,0.0f);
//            camera.moveLeft(0.1f);
//            camera.setPosition();
            if (forwardDegX >= 180f){
                xDegreeOffset = -1;
            }
            if (forwardDegX <= 0){
                xDegreeOffset = 1;
            }
            if (forwardDegZ >= 180f){
                zDegreeOffset = -1;
            }
            if (forwardDegZ <= 0){
                zDegreeOffset = 1;
            }
            forwardDegX += xDegreeOffset;
            forwardDegZ += zDegreeOffset;

            float feetDirectionalityX = (forwardX - forwardDegX)/90;
            System.out.println(feetDirectionalityX);
            float feetDirectionalityZ = (forwardZ - forwardDegZ)/90;
            System.out.println(feetDirectionalityZ);
        }

        if (window.isKeyPressed(GLFW_KEY_S)){
            Vector3f goombaTempCenter = objectsGoomba.get(0).updateCenterPoint();
            //Vector3f goombaCurveTempCenter = objectsGoombaCurves.get(0).updateCenterPoint();
            objectsGoomba.get(0).translateObject(
                    goombaTempCenter.x*-1,
                    goombaTempCenter.y*-1,
                    goombaTempCenter.z*-1);
            objectsGoomba.get(0).rotateObject((float) Math.toRadians(anchor2), 0.0f, 1.0f, 0.0f);
            objectsGoomba.get(0).translateObject(
                    goombaTempCenter.x*1,
                    goombaTempCenter.y*1,
                    goombaTempCenter.z*1);

            Vector3f booTempCenter = objectsBoo.get(0).updateCenterPoint();
            //Vector3f booCurveTempCenter = objectsBooCurves.get(0).updateCenterPoint();
            objectsBoo.get(0).translateObject(
                    booTempCenter.x*-1,
                    booTempCenter.y*-1,
                    booTempCenter.z*-1);
            objectsBoo.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            objectsBoo.get(0).translateObject(
                    booTempCenter.x*1,
                    booTempCenter.y*1,
                    booTempCenter.z*1);


            Vector3f bombTempCenter = bob_omb.get(0).updateCenterPoint();
            bob_omb.get(0).translateObject(
                    bombTempCenter.x*-1,
                    bombTempCenter.y*-1,
                    bombTempCenter.z*-1);
            bob_omb.get(0).rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            bob_omb.get(0).translateObject(
                    bombTempCenter.x*1,
                    bombTempCenter.y*1,
                    bombTempCenter.z*1);



        }
        if(window.isKeyPressed(GLFW_KEY_Z)){
            Vector3f bombTempCenter = bob_omb.get(0).updateCenterPoint();
            bob_omb.get(0).translateObject(
                    bombTempCenter.x*-1,
                    bombTempCenter.y*-1,
                    bombTempCenter.z*-1);
            bob_omb.get(0).rotateObject((float) Math.toRadians(10f), 1.0f, 0.0f, 0.0f);
            bob_omb.get(0).translateObject(
                    bombTempCenter.x*1,
                    bombTempCenter.y*1,
                    bombTempCenter.z*1);
        }

        // do animation for goomba
        if (window.isKeyPressed(GLFW_KEY_D)) {
            AnimateStopAnytime();
        }


        // Animate bob omb
        if(window.isKeyPressed(GLFW_KEY_F)){
//            bob_omb.get(0).translateObject(0.1f,0.0f,0.0f);
            animation();
//            camera.moveRight(0.1f);
        }
//        if(window.isKeyPressed(GLFW_KEY_V)){
//            bob_omb.get(0).translateObject(-0.1f,0.0f,0.0f);
//            animation();
//            camera.moveLeft(0.1f);
//        }
        if(window.isKeyPressed(GLFW_KEY_E)){

            if(counter2 == 20){
                anchor2=anchor2*-1;
            }
            if(counter2==40){
                anchor2=0;
            }


            bob_omb.get(0).rotateObject(anchor2*(float)Math.toRadians(1.3f),0.0f,1.0f,0.0f );

            counter2+=1;

        }



        // animate Boo
        if(window.isKeyPressed(GLFW_KEY_G)){

            if (counter == limiter){
                anchor = anchor * -1;
            }
            if (counter == 30){
                counter = 0;
            }
            objectsBoo.get(0).getChildObject().get(4).rotateObject(anchor * (float) Math.toRadians(0.15f),0.0f,0.0f,1.0f);
            objectsBoo.get(0).getChildObject().get(5).rotateObject(anchor * (float) Math.toRadians(0.15f),0.0f,0.0f,1.0f);
            objectsBoo.get(0).rotateObject(anchor * (float) Math.toRadians(0.15f),0.0f,0.0f,1.0f);
            //objectsBooCurves.get(0).rotateObject(anchor * (float) Math.toRadians(0.35f),0.0f,0.0f,1.0f);
            counter += 1 ;
        }



        if (window.isKeyPressed(GLFW_KEY_H)){
            objectsBoo.get(0).translateObject(0.0f, 0.0f, 0.1f);
            objectsGoomba.get(0).translateObject(0.0f, 0.0f, 0.1f);
            bob_omb.get(0).translateObject(0.0f, 0.0f, 0.1f);
        }

        if (window.isKeyPressed(GLFW_KEY_J)){
            objectsBoo.get(0).translateObject(0.0f, 0.0f, -0.1f);
            objectsGoomba.get(0).translateObject(0.0f, 0.0f, -0.1f);
            bob_omb.get(0).translateObject(0.0f, 0.0f, -0.1f);
        }

        if(window.isKeyPressed(GLFW_KEY_C)){
            camera.moveRight(0.1f);
                   }
        if(window.isKeyPressed(GLFW_KEY_X)){
            camera.moveLeft(0.1f);
        }

        // if the user release the left mouse button it will stop the moving state
        if (window.getMouseInput().isLeftButtonReleased()){
            inMovingState = false;
        }
        if (window.getMouseInput().isLeftButtonPressed()){
            Vector2f pos = window.getMouseInput().getCurrentPos();

            // normalize
            pos.x = (pos.x - (window.getWidth())/2.0f) / (window.getWidth()/2.0f);
            pos.y = (pos.y - (window.getHeight())/2.0f) / (-window.getHeight()/2.0f);

            if ((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 1 || pos.y < -0.97))){
                // check if you clicked the rectangle
                boolean inRectangle = false;

                // check if you tried to place another rectangle to close
                boolean inRadius = false;

                // index of the clicked rectangle
                int rectangleToMove = 0;

//                for (Objects object : objectsMovable){
//                    if (object.withinRadius(pos) == true){
//                        inRadius = true;
//                    }
//                    if (object.withinRectangle(pos) == true){
//                        inRectangle = true;
//                        break;
//                    }
//                    rectangleToMove += 1;
//                }

                // when the user clicks a rectangle it enters a moving state to move the rectangle
                if (inRectangle == true){
                    inMovingState = true;
                    currentRectangle = rectangleToMove;
                }

//                // move the rectangle and line during moving state
//                if (inMovingState == true){
//                    Vector3f position = new Vector3f(pos, 0.0f);
//                    objectsMovable.get(currentRectangle).move(position);
//                    objectsControl.get(0).changeVerticePos(currentRectangle, position);
////                    objectsCurve.get(0).changeVerticePos(currentRectangle, position);
//                }
//
                // only enable clicking when the point is not in a rectangle and not in a moving state
                if (inRectangle == false && inRadius == false && inMovingState == false){
                    System.out.println("x : "+pos.x+" y : "+pos.y);
//                    objectsControl.get(0).addVertices(new Vector3f(pos, 0.0f));
//                    objectsCurve.get(0).addVertices(new Vector3f(pos, 0.0f));
//                    objectsMovable.add(new MovableRectangle(
//                            Arrays.asList(
//                                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                                    new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                            ),
//                            // what you put here doesn't matter leave it empty if u want
//                            new ArrayList<>(),
//                            // this is color
//                            new Vector4f(1.0f, 0.0f, 0.0f, 1.0f),
//                            // this is radius
//                            0.04f,
//                            // this is the circle's center
//                            new Vector3f(pos,0.0f)
//                    ));
                }
            }
        }
    }



    // make this function, so it's easier to write reports about it
    private void AnimateStopAnytime(){
        // determine which foot to begin with
        if (feetMovDeg >= 45f){
            feetMov = -1f;
        }
        if (feetMovDeg <= -45f){
            feetMov = 1f;
        }

        float feetDirectionalityX = (forwardX - forwardDegX)/90;
        System.out.println(feetDirectionalityX);
        float feetDirectionalityZ = (forwardZ - forwardDegZ)/90;
        System.out.println(feetDirectionalityZ);

        // exclude the feet from parent rotation for anim purposes
        objectsGoomba.get(0).getChildObject().get(2).setExcludeRotate(true);
        objectsGoomba.get(0).getChildObject().get(3).setExcludeRotate(true);

        Vector3f goombaTempCenter = objectsGoomba.get(0).updateCenterPoint();
        //Vector3f goombaCurveTempCenter = objectsGoombaCurves.get(0).updateCenterPoint();
        objectsGoomba.get(0).translateObject(
                goombaTempCenter.x*-1,
                goombaTempCenter.y*-1,
                goombaTempCenter.z*-1);
        objectsGoomba.get(0).rotateObject((float) Math.toRadians(feetMov * 0.1), 0.0f, 1.0f, 0.0f);
        objectsGoomba.get(0).translateObject(
                goombaTempCenter.x*1,
                goombaTempCenter.y*1,
                goombaTempCenter.z*1);
        // move everything else
        //objectsGoomba.get(0).rotateObject((float) Math.toRadians(feetMov * 0.1), 0.0f, 0.0f, -1.0f);
        //objectsGoombaCurves.get(0).rotateObject((float) Math.toRadians(feetMov * 0.1), 0.0f, 0.0f, -1.0f);

        // move the feet
        Vector3f tempCenterPoint1 = objectsGoomba.get(0).getChildObject().get(2).updateCenterPoint();
        Vector3f tempCenterPoint2 = objectsGoomba.get(0).getChildObject().get(3).updateCenterPoint();
        objectsGoomba.get(0).getChildObject().get(2).translateObject(
                tempCenterPoint1.x * -1,
                tempCenterPoint1.y * -1,
                tempCenterPoint1.z * -1);
        objectsGoomba.get(0).getChildObject().get(2).rotateObject(
                (float) Math.toRadians(feetMov * -1), feetDirectionalityX, 0.0f, feetDirectionalityZ);
        objectsGoomba.get(0).getChildObject().get(2).translateObject(
                tempCenterPoint1.x * +1,
                tempCenterPoint1.y * +1,
                tempCenterPoint1.z * +1);
        objectsGoomba.get(0).getChildObject().get(3).translateObject(
                tempCenterPoint2.x * -1,
                tempCenterPoint2.y * -1,
                tempCenterPoint2.z * -1);
        objectsGoomba.get(0).getChildObject().get(3).rotateObject(
                (float) Math.toRadians(feetMov), feetDirectionalityX, 0.0f, feetDirectionalityZ);
        objectsGoomba.get(0).getChildObject().get(3).translateObject(
                tempCenterPoint2.x * +1,
                tempCenterPoint2.y * +1,
                tempCenterPoint2.z * +1);

        // keep track of the amount of degrees moved
        feetMovDeg += feetMov;

        // include the feet from parent rotation after anim
        objectsGoomba.get(0).getChildObject().get(2).setExcludeRotate(false);
        objectsGoomba.get(0).getChildObject().get(3).setExcludeRotate(false);

    }

    public void animation(){
        if (feetMovDeg >= 45f){
            feetMov = -1f;
        }
        if (feetMovDeg <= -45f){
            feetMov = 1f;
        }

        float feetDirectionalityX = (forwardX - forwardDegX)/90;
        System.out.println(feetDirectionalityX);
        float feetDirectionalityZ = (forwardZ - forwardDegZ)/90;
        System.out.println(feetDirectionalityZ);

        // exclude the feet from parent rotation for anim purposes
        bob_omb.get(0).getChildObject().get(2).setExcludeRotate(true);
        bob_omb.get(0).getChildObject().get(3).setExcludeRotate(true);



        // move everything else
//        objectsBox.get(0).rotateObject((float) Math.toRadians(feetMov * 0.1), 0.0f, 0.0f, -1.0f);
//        objectsCurve.get(0).rotateObject((float) Math.toRadians(feetMov * 0.1), 0.0f, 0.0f, -1.0f);

        // move the feet
        Vector3f tempCenterPoint1 = bob_omb.get(0).getChildObject().get(2).updateCenterPoint();
        Vector3f tempCenterPoint2 = bob_omb.get(0).getChildObject().get(3).updateCenterPoint();
        bob_omb.get(0).getChildObject().get(2).translateObject(
                tempCenterPoint1.x * -1,
                tempCenterPoint1.y * -1,
                tempCenterPoint1.z * -1);
        bob_omb.get(0).getChildObject().get(2).rotateObject(
                (float) Math.toRadians(feetMov * -1), 0.0f, 0.0f,feetDirectionalityZ);
        bob_omb.get(0).getChildObject().get(2).translateObject(
                tempCenterPoint1.x * +1,
                tempCenterPoint1.y * +1,
                tempCenterPoint1.z * +1);
        bob_omb.get(0).getChildObject().get(3).translateObject(
                tempCenterPoint2.x * -1,
                tempCenterPoint2.y * -1,
                tempCenterPoint2.z * -1);
        bob_omb.get(0).getChildObject().get(3).rotateObject(
                (float) Math.toRadians(feetMov * 1),0.0f, 0.0f,feetDirectionalityZ);
        bob_omb.get(0).getChildObject().get(3).translateObject(
                tempCenterPoint2.x * +1,
                tempCenterPoint2.y * +1,
                tempCenterPoint2.z * +1);

        // keep track of the amount of degrees moved
        feetMovDeg += feetMov;

        // include the feet from parent rotation after anim
        bob_omb.get(0).getChildObject().get(2).setExcludeRotate(false);
        bob_omb.get(0).getChildObject().get(3).setExcludeRotate(false);
    }

    public void loop() {
        while (window.isOpen()) {
            window.update();
            glClearColor(0.3f,
                    0.3f, 0.3f,
                    3.0f);
            GL.createCapabilities();
            input();

            //code no color

//            for (Object2d object : objectsControl) {
//                object.drawLine();
//            }

//
//            for (Objects object : objectsMovable) {
//                object.draw();
//            }

            for (Objects object : objectsGoomba) {
                object.draw(camera, projection);
            }

            for (Objects object : objectsGoombaCurves) {
                object.drawLine(camera, projection);
            }

            for(Objects object: objectsBoo){
                object.draw(camera,projection);
            }

            for (Objects object : objectsBooCurves) {
                object.drawLine(camera,projection);
            }

            for(Objects objects:Background){
                objects.draw(camera,projection);
//                objects.drawLine(camera,projection);
            }


            for(Objects objects:BerzierBackground){
                objects.drawLine(camera,projection);
            }

            for(Objects obj:bob_omb){
                obj.draw(camera,projection);
            }
            for(Objects obj:bob_ombberzier){
                obj.drawLine(camera,projection);
            }

//            for(Object2d object: objects){
//                object.drawwithverticescolor();
//            }

            glDisableVertexAttribArray(0);
            glfwPollEvents();
        }
//        model = model.mul(new Matrix4f().rotate((float) Math.toRadians(1.0f), 1,0,0));
//        model = model.mul(new Matrix4f().rotate((float) Math.toRadians(0.5f), 0,1,0));
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
