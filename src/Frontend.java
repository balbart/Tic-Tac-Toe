import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frontend extends JFrame{
    private JButton b00;
    private JButton b01;
    private JButton b02;
    private JButton b10;
    private JButton b11;
    private JButton b12;
    private JButton b20;
    private JButton b21;
    private JButton b22;
    private JPanel mainPanel;
    private Backend backend;
    private JButton[][] buttonMatrix = {{b00, b01, b02},
                                        {b10, b11, b12},
                                        {b20, b21, b22}};

    Frontend(){
        backend = new Backend(buttonMatrix);
        setWindow();
        setListeners();
    }

    void setWindow(){
        setContentPane(mainPanel);
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Game");
        setResizable(false);
        setVisible(true);
    }

    void setListeners(){
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++){
                buttonMatrix[i][j].addActionListener(new Listener(buttonMatrix[i][j], backend));
            }
        }
    }
}

class Listener implements ActionListener{
    private JButton button;
    private Backend backend;
    Listener(JButton button, Backend backend) {
        this.button = button;
        this.backend = backend;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        backend.onClick(button);
    }
}
