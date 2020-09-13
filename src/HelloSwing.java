import javax.swing.JFrame;

class HelloSwing extends JFrame {
    public static void main(String[] args) {
        new HelloSwing();
    }

    HelloSwing() {
        setTitle("Hello, Swing!");  // заголовок окна
        setDefaultCloseOperation(EXIT_ON_CLOSE); // при закрытии
        setSize(500, 300); // размеры окна
        setLocationRelativeTo(null); // позиция на экране
        setVisible(true); // сделать видимым
    }
}
