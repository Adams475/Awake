import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Optional;

class Interface {

    private static long pid = -1;

    static void initializeGUI() {

        JFrame jframe = new JFrame();
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jframe.setSize(480, 480);
        jframe.setTitle("Awake >.<");
        jframe.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JButton runButton = new JButton("Run");
        runButton.addActionListener(actionEvent -> {
            try {
                Process process = Runtime.getRuntime()
                        .exec("javac -cp src src\\Driver.java");
                process = Runtime.getRuntime()
                        .exec("java -cp src Driver");
                pid = process.pid();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        buttonPanel.add(runButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(actionEvent -> {
            if (pid != -1) {
                System.out.println("Killing pid " + pid);
                Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(pid);
                optionalProcessHandle.ifPresent(ProcessHandle::destroy);
            }
        });
        buttonPanel.add(stopButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(actionEvent -> {
            if (pid != -1) {
                System.out.println("Killing pid " + pid);
                Optional<ProcessHandle> optionalProcessHandle = ProcessHandle.of(pid);
                optionalProcessHandle.ifPresent(ProcessHandle::destroy);
            }
            System.exit(0);
        });
        buttonPanel.add(exitButton);

        JPanel title = new JPanel();
        title.add(new JLabel("!Sleep"));

        jframe.add(title, BorderLayout.NORTH);
        jframe.add(buttonPanel, BorderLayout.SOUTH);
        jframe.pack();
    }
}
