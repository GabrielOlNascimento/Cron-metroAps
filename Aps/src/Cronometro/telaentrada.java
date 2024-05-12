package Cronometro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class telaentrada extends JFrame implements ActionListener {
    private JTextField textNome;
    private JTextField textEquipe;
    private JButton botaoConfirmar;
    private String valorInicial;
    private String valorInicial2;

    public String[] getValores() {
        return new String[]{valorInicial, valorInicial2};
    }

    public telaentrada() {
        setTitle("Cadastro do Piloto");
        setSize(300, 200); // Aumentando a altura para acomodar os dois campos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Usando o BoxLayout com eixo Y

        textNome = new JTextField(10);
        textNome.setPreferredSize(new Dimension(2, 2)); // Definindo o tamanho preferido do campo de texto
        add(new JLabel("Nome do Piloto:")); // Adicionando um rótulo para o campo
        add(textNome);

        textEquipe = new JTextField(20);
        textEquipe.setPreferredSize(new Dimension(2, 2)); // Definindo o tamanho preferido do campo de texto
        add(new JLabel("Nome da Equipe:")); // Adicionando um rótulo para o campo
        add(textEquipe);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(this);
        buttonPanel.add(botaoConfirmar); // Adicionando o botão ao painel

        add(buttonPanel); // Adicionando o painel ao frame

        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoConfirmar) {
            valorInicial = textNome.getText(); // Obtendo o valor do primeiro campo de texto
            valorInicial2 = textEquipe.getText(); // Obtendo o valor do segundo campo de texto
            dispose(); // Fechar a tela de entrada
            SwingUtilities.invokeLater(() -> new cronometro(getValores())); // Criar o cronômetro com os valores iniciais
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(telaentrada::new);
    }
}
