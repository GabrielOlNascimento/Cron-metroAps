package Cronometro;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Dao.voltascarrinhoDAO;
import entity.voltascarrinho;

import java.awt.datatransfer.Clipboard;


public class cronometro extends JFrame implements ActionListener {
    private JLabel labelTempo;
    private JLabel labelValoresSalvos; 
    private JButton botaoIniciar, botaoSalvar, botaoParar;
    private Timer timer;
    private long startTime, elapsedTime, savedTime;
    private String valorInicial;
    private String valorInicial2;
    private int proximoId = 1;
    private int proximaVolta = 1;
    private int segundos;
    private int segundosAtual;
    private int volta;
    



    private String formatarTempo(long millis) {
        int milissegundos = (int) millis % 1000;
        int segundos = (int) (millis / 1000) % 60;
        int minutos = (int) ((millis / (1000 * 60)) % 60);
        int horas = (int) ((millis / (1000 * 60 * 60)) % 24);
        return String.format("%02d:%02d:%02d:%03d", horas, minutos, segundos, milissegundos);

        
    }
    public static void clearClipboard() {
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection emptySelection = new StringSelection("");
            clipboard.setContents(emptySelection, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
 

    public cronometro(String[] valores) {



        this.valorInicial = valores[0];
        this.valorInicial2 = valores[1];
        setTitle("Cronômetro");
        setSize(400, 400); // Aumentei a altura para acomodar o novo JLabel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        labelTempo = new JLabel("00:00:00:000");
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

        timer = new Timer(10, this); // Atualiza a cada 10 milissegundos

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoIniciar) {
            startTime = System.currentTimeMillis();
            timer.start();
        } else if (e.getSource() == botaoSalvar) {
            savedTime = elapsedTime;
            segundosAtual = segundos;
            System.out.println("Tempo salvo: " + formatarTempo(savedTime));
            System.out.println(valorInicial);
            System.out.println(valorInicial2);
            segundos = (int) (savedTime / 1000) % 60;
            System.out.println("Volta 1: " + segundos);
            System.out.println("segundos:" + segundosAtual);
            volta = segundos - segundosAtual;
            System.out.println("volta 2:" + volta);

            voltascarrinho v = new voltascarrinho();
            v.setId(proximoId);
            proximoId++;
            v.setVolta_numero(proximaVolta);
            proximaVolta++;
            v.setTempo_total(formatarTempo(savedTime));
            v.setPiloto(valorInicial);
            v.setEquipe(valorInicial2);
            v.setVolta_tempo(volta);
            new voltascarrinhoDAO().inserirInfo(v);

            // Atualize o JLabel com os valores salvos
            labelValoresSalvos.setText("<html>Valores salvos:<br>" +
                    "Tempo Total: " + formatarTempo(savedTime) + "<br>" +
                    "Tempo Volta 1: " + segundosAtual + "<br>" +
                    "Tempo Volta 2: " + volta + "<br>" +
                    "Piloto: " + valorInicial + "<br>" +
                    "Equipe: " + valorInicial2 + "<br");
                    

        } else if (e.getSource() == botaoParar) {
            timer.stop();
            labelValoresSalvos.setText("<html>Valores salvos:<br>" +
            "Tempo Total: " + formatarTempo(savedTime) + "<br>" +
            "Tempo Volta 1: " + segundosAtual + "<br>" +
            "Tempo Volta 2: " + volta + "<br>" +
            "Piloto: " + valorInicial + "<br>" +
            "Equipe: " + valorInicial2 + "<br");
        } else if (e.getSource() == timer) {
            elapsedTime = System.currentTimeMillis() - startTime;
            labelTempo.setText(formatarTempo(elapsedTime));
        }
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(telaentrada::new);
        Timer clipboardTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearClipboard();
            }
        });
        clipboardTimer.start();
    }
}




