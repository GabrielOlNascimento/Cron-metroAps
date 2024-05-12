package Cronometro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Dao.voltascarrinhoDAO;
import entity.voltascarrinho;

public class cronometro extends JFrame implements ActionListener {
    private JLabel labelTempo;
    private JLabel labelValoresSalvos; // Novo JLabel para mostrar os valores salvos
    private JButton botaoIniciar, botaoSalvar, botaoParar;
    private Timer timer;
    private long startTime, elapsedTime, savedTime;
    private String valorInicial;
    private String valorInicial2;
    private int proximoId = 1;
    private int proximaVolta = 1;

    public cronometro(String[] valores) {
        this.valorInicial = valores[0];
        this.valorInicial2 = valores[1];

        setTitle("Cronômetro");
        setSize(300, 250); // Aumentei a altura para acomodar o novo JLabel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        labelTempo = new JLabel("00:00:00");
        labelTempo.setFont(new Font("Arial", Font.PLAIN, 32));
        labelTempo.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelTempo, BorderLayout.CENTER);

        labelValoresSalvos = new JLabel("Valores salvos:");
        add(labelValoresSalvos, BorderLayout.NORTH); // Adicionando o JLabel ao topo

        botaoIniciar = new JButton("Iniciar");
        botaoIniciar.addActionListener(this);
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(this);
        botaoParar = new JButton("Parar");
        botaoParar.addActionListener(this);

        JPanel botoesPanel = new JPanel();
        botoesPanel.setLayout(new FlowLayout());
        botoesPanel.add(botaoIniciar);
        botoesPanel.add(botaoSalvar);
        botoesPanel.add(botaoParar);
        add(botoesPanel, BorderLayout.SOUTH);

        timer = new Timer(1000, this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoIniciar) {
            startTime = System.currentTimeMillis();
            timer.start();
        } else if (e.getSource() == botaoSalvar) {
            savedTime = elapsedTime;
            System.out.println("Tempo salvo: " + formatarTempo(savedTime));
            System.out.println(valorInicial);
            System.out.println(valorInicial2);
            voltascarrinho v = new voltascarrinho();
            v.setId(proximoId);
            proximoId++;
            v.setVolta_numero(proximaVolta);
            proximaVolta++;
            v.setTempo_total(formatarTempo(savedTime));
            v.setPiloto(valorInicial);
            v.setEquipe(valorInicial2);
            new voltascarrinhoDAO().inserirInfo(v);

            // Atualize o JLabel com os valores salvos
            labelValoresSalvos.setText("<html>Valores salvos:<br>" +
                    "Tempo: " + formatarTempo(savedTime) + "<br>" +
                    "Piloto: " + valorInicial + "<br>" +
                    "Equipe: " + valorInicial2 + "</html>");

        } else if (e.getSource() == botaoParar) {
            timer.stop();
        } else if (e.getSource() == timer) {
            elapsedTime = System.currentTimeMillis() - startTime;
            labelTempo.setText(formatarTempo(elapsedTime));
        }
    }

    private String formatarTempo(long millis) {
        int segundos = (int) (millis / 1000) % 60;
        int minutos = (int) ((millis / (1000 * 60)) % 60);
        int horas = (int) ((millis / (1000 * 60 * 60)) % 24);
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(telaentrada::new);
    }

}
