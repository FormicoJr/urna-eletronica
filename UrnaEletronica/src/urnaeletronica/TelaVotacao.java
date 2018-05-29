/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urnaeletronica;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author MARCELO
 */
public class TelaVotacao extends javax.swing.JFrame {

    String nCandidato;
    int numCand;
    String digito;

    public void audio() {
        InputStream inAudio;
        try {
            inAudio = new FileInputStream(new File("C:\\AudioUrna\\AudioUrna.wav"));
            AudioStream audio = new AudioStream(inAudio);
            AudioPlayer.player.start(audio);
            Thread.sleep(2000);
        } catch (Exception erro) {
        }
    }

    public void fotoCandidato(String foto) {
        File img = new File(foto);
        ImageIcon candidato = new ImageIcon(img.getPath());
        LBL_FOTO.setIcon(candidato);
    }

    public void fotoVice(String foto) {
        File img = new File(foto);
        ImageIcon candidato = new ImageIcon(img.getPath());
        LBL_FOTOVICE.setIcon(candidato);

    }

    public void conexaoValidacaoCandidato(String numero) {
        if (LBL_CARGO.getText().equals("DEPUTADO ESTADUAL (a)")) {
            if (TXT_NUM1.getText().equals("")) {
                TXT_NUM1.setText(numero);
            } else if (TXT_NUM2.getText().equals("")) {
                TXT_NUM2.setText(numero);
            } else if (TXT_NUM3.getText().equals("")) {
                TXT_NUM3.setText(numero);
            } else if (TXT_NUM4.getText().equals("")) {
                TXT_NUM4.setText(numero);
            } else if (TXT_NUM5.getText().equals("")) {
                TXT_NUM5.setText(numero);

                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText()
                        + TXT_NUM4.getText() + TXT_NUM5.getText();

                String sql = "select * from deputado_estadual where EST_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setString(1, nCandidato);
                    ResultSet busca = comando.executeQuery();

                    if (busca.next() == true) {
                        String nomeCand = busca.getString("EST_NOME");
                        String partido = busca.getString("EST_SIGPARTIDO");
                        String foto = busca.getString("EST_FOTO");

                        LBL_NOME.setText("NOME:");
                        LBL_PARTIDO.setText("PARTIDO:");
                        LBL_NOMECANDIDATO.setText(nomeCand);
                        LBL_SIGLAPARTIDO.setText(partido);
                        fotoCandidato(foto);
                    } else {
                        LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
                    }
                    comando.close();
                    conexao.close();

                } catch (SQLException erro) {
                    erro.printStackTrace();
                }

            }
        } else if (LBL_CARGO.getText().equals("DEPUTADO FEDERAL (a)")) {
            if (TXT_NUM1.getText().equals("")) {
                TXT_NUM1.setText(numero);
            } else if (TXT_NUM2.getText().equals("")) {
                TXT_NUM2.setText(numero);
            } else if (TXT_NUM3.getText().equals("")) {
                TXT_NUM3.setText(numero);
            } else if (TXT_NUM4.getText().equals("")) {
                TXT_NUM4.setText(numero);

                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText()
                        + TXT_NUM4.getText();

                String sql = "select * from deputado_federal where FED_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setString(1, nCandidato);
                    ResultSet busca = comando.executeQuery();

                    if (busca.next() == true) {
                        String nomeCand = busca.getString("FED_NOME");
                        String partido = busca.getString("FED_SIGPARTIDO");
                        String foto = busca.getString("FED_FOTO");

                        LBL_NOME.setText("NOME:");
                        LBL_PARTIDO.setText("PARTIDO:");
                        LBL_NOMECANDIDATO.setText(nomeCand);
                        LBL_SIGLAPARTIDO.setText(partido);
                        fotoCandidato(foto);
                    } else {
                        LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
                    }
                    comando.close();
                    conexao.close();

                } catch (SQLException erro) {
                    erro.printStackTrace();
                }
            }
        } else if (LBL_CARGO.getText().equals("SENADOR (a)")) {
            if (TXT_NUM1.getText().equals("")) {
                TXT_NUM1.setText(numero);
            } else if (TXT_NUM2.getText().equals("")) {
                TXT_NUM2.setText(numero);
            } else if (TXT_NUM3.getText().equals("")) {
                TXT_NUM3.setText(numero);

                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText();

                String sql = "select * from senador where SEN_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setString(1, nCandidato);
                    ResultSet busca = comando.executeQuery();

                    if (busca.next() == true) {
                        String nomeCand = busca.getString("SEN_NOME");
                        String partido = busca.getString("SEN_SIGPARTIDO");
                        String foto = busca.getString("SEN_FOTO");

                        LBL_NOME.setText("NOME:");
                        LBL_PARTIDO.setText("PARTIDO:");
                        LBL_NOMECANDIDATO.setText(nomeCand);
                        LBL_SIGLAPARTIDO.setText(partido);
                        fotoCandidato(foto);
                    } else {
                        LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
                    }
                    comando.close();
                    conexao.close();

                } catch (SQLException erro) {
                    erro.printStackTrace();
                }
            }
        } else if (LBL_CARGO.getText().equals("GOVERNADOR (a)")) {
            if (TXT_NUM1.getText().equals("")) {
                TXT_NUM1.setText(numero);
            } else if (TXT_NUM2.getText().equals("")) {
                TXT_NUM2.setText(numero);

                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText()
                        + TXT_NUM4.getText() + TXT_NUM5.getText();

                String sql = "select * from governador where GOV_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setString(1, nCandidato);
                    ResultSet busca = comando.executeQuery();

                    if (busca.next() == true) {
                        String nomeCand = busca.getString("GOV_NOME");
                        String partido = busca.getString("GOV_SIGPARTIDO");
                        String foto = busca.getString("GOV_FOTO");
                        String vice = busca.getString("GOV_VICE");
                        String fotovice = busca.getString("GOV_FOTO_VICE");

                        LBL_NOME.setText("NOME:");
                        LBL_PARTIDO.setText("PARTIDO:");
                        LBL_VICE.setText("VICE:");
                        LBL_NOMECANDIDATO.setText(nomeCand);
                        LBL_SIGLAPARTIDO.setText(partido);
                        LBL_NOMEVICE.setText(vice);
                        fotoCandidato(foto);
                        fotoVice(fotovice);
                    } else {
                        LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
                    }
                    comando.close();
                    conexao.close();

                } catch (SQLException erro) {
                    erro.printStackTrace();
                }
            }
        } else if (LBL_CARGO.getText().equals("PRESIDENTE (a)")) {
            if (TXT_NUM1.getText().equals("")) {
                TXT_NUM1.setText(numero);
            } else if (TXT_NUM2.getText().equals("")) {
                TXT_NUM2.setText(numero);

                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText()
                        + TXT_NUM4.getText() + TXT_NUM5.getText();

                String sql = "select * from presidente where PRE_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setString(1, nCandidato);
                    ResultSet busca = comando.executeQuery();

                    if (busca.next() == true) {
                        String nomeCand = busca.getString("PRE_NOME");
                        String partido = busca.getString("PRE_SIGPARTIDO");
                        String foto = busca.getString("PRE_FOTO");
                        String vice = busca.getString("PRE_VICE");
                        String fotovice = busca.getString("PRE_FOTO_VICE");

                        LBL_NOME.setText("NOME:");
                        LBL_PARTIDO.setText("PARTIDO:");
                        LBL_VICE.setText("VICE:");
                        LBL_NOMECANDIDATO.setText(nomeCand);
                        LBL_SIGLAPARTIDO.setText(partido);
                        LBL_NOMEVICE.setText(vice);
                        fotoCandidato(foto);
                        fotoVice(fotovice);
                    } else {
                        LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
                    }
                    comando.close();
                    conexao.close();

                } catch (SQLException erro) {
                    erro.printStackTrace();
                }
            }
        }
    }

    public void limpaTela() {
        LBL_NOME.setText("");
        LBL_NOMECANDIDATO.setText("");
        LBL_PARTIDO.setText("");
        LBL_SIGLAPARTIDO.setText("");
        LBL_VICE.setText("");
        LBL_NOMEVICE.setText("");
        LBL_FOTO.setIcon(null);
        LBL_FOTOVICE.setIcon(null);
    }

    public void txtCandidatoVisible(String cargo) {
        if (cargo.equals("DEPUTADO ESTADUAL (a)")) {
            TXT_NUM1.setVisible(true);
            TXT_NUM2.setVisible(true);
            TXT_NUM3.setVisible(true);
            TXT_NUM4.setVisible(true);
            TXT_NUM5.setVisible(true);
        } else if (cargo.equals("DEPUTADO FEDERAL (a)")) {
            TXT_NUM1.setVisible(true);
            TXT_NUM2.setVisible(true);
            TXT_NUM3.setVisible(true);
            TXT_NUM4.setVisible(true);
            TXT_NUM5.setVisible(false);
        } else if (cargo.equals("SENADOR (a)")) {
            TXT_NUM1.setVisible(true);
            TXT_NUM2.setVisible(true);
            TXT_NUM3.setVisible(true);
            TXT_NUM4.setVisible(false);
            TXT_NUM5.setVisible(false);
        } else if (cargo.equals("GOVERNADOR (a)")) {
            TXT_NUM1.setVisible(true);
            TXT_NUM2.setVisible(true);
            TXT_NUM3.setVisible(false);
            TXT_NUM4.setVisible(false);
            TXT_NUM5.setVisible(false);
        } else if (cargo.equals("PRESIDENTE (a)")) {
            TXT_NUM1.setVisible(true);
            TXT_NUM2.setVisible(true);
            TXT_NUM3.setVisible(false);
            TXT_NUM4.setVisible(false);
            TXT_NUM5.setVisible(false);
        }
    }

    public void limpaTxtCandidato() {
        TXT_NUM1.setText("");
        TXT_NUM2.setText("");
        TXT_NUM3.setText("");
        TXT_NUM4.setText("");
        TXT_NUM5.setText("");
    }

    public TelaVotacao() {
        initComponents();
        TXT_NUM1.setEditable(false);
        TXT_NUM2.setEditable(false);
        TXT_NUM3.setEditable(false);
        TXT_NUM4.setEditable(false);
        TXT_NUM5.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PNL_TOTAL = new javax.swing.JPanel();
        PNL_DIGITOS = new javax.swing.JPanel();
        SPD_DIGITOS = new javax.swing.JSeparator();
        LBL_BRASAO = new javax.swing.JLabel();
        LBL_JUSTICA = new javax.swing.JLabel();
        LBL_ELEITORAL = new javax.swing.JLabel();
        BTN_1 = new javax.swing.JButton();
        BTN_2 = new javax.swing.JButton();
        BTN_3 = new javax.swing.JButton();
        BTN_6 = new javax.swing.JButton();
        BTN_5 = new javax.swing.JButton();
        BTN_4 = new javax.swing.JButton();
        BTN_9 = new javax.swing.JButton();
        BTN_8 = new javax.swing.JButton();
        BTN_7 = new javax.swing.JButton();
        BTN_0 = new javax.swing.JButton();
        BTN_BRANCO = new javax.swing.JButton();
        BTN_CORRIGE = new javax.swing.JButton();
        BTN_CONFIRMA = new javax.swing.JButton();
        PNL_TELA = new javax.swing.JPanel();
        LBL_FOTO = new javax.swing.JLabel();
        LBL_SEUVOTO = new javax.swing.JLabel();
        LBL_CARGO = new javax.swing.JLabel();
        LBL_NUMERO = new javax.swing.JLabel();
        TXT_NUM1 = new javax.swing.JTextField();
        TXT_NUM2 = new javax.swing.JTextField();
        TXT_NUM3 = new javax.swing.JTextField();
        TXT_NUM4 = new javax.swing.JTextField();
        TXT_NUM5 = new javax.swing.JTextField();
        LBL_NOMECANDIDATO = new javax.swing.JLabel();
        LBL_SIGLAPARTIDO = new javax.swing.JLabel();
        LBL_NOME = new javax.swing.JLabel();
        LBL_PARTIDO = new javax.swing.JLabel();
        LBL_APERTE = new javax.swing.JLabel();
        LBL_VERDE = new javax.swing.JLabel();
        LBL_PARA1 = new javax.swing.JLabel();
        LBL_CONFIRMAR = new javax.swing.JLabel();
        LBL_LARANJA = new javax.swing.JLabel();
        LBL_CORRIGIR = new javax.swing.JLabel();
        LBL_PARA2 = new javax.swing.JLabel();
        LBL_FOTOVICE = new javax.swing.JLabel();
        LBL_VICE = new javax.swing.JLabel();
        LBL_NOMEVICE = new javax.swing.JLabel();
        SPD_CONFIRMACAO = new javax.swing.JSeparator();
        LBL_VOTOBRANCONULO = new javax.swing.JLabel();
        LBL_AVISONUMERO = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PNL_TOTAL.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        PNL_DIGITOS.setBackground(new java.awt.Color(153, 153, 153));
        PNL_DIGITOS.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        LBL_BRASAO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/urnaeletronica/imagens/BrasaoJusticaEleitoral.png"))); // NOI18N
        LBL_BRASAO.setMaximumSize(new java.awt.Dimension(79, 74));
        LBL_BRASAO.setMinimumSize(new java.awt.Dimension(79, 74));
        LBL_BRASAO.setPreferredSize(new java.awt.Dimension(79, 74));

        LBL_JUSTICA.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_JUSTICA.setText("JUSTIÇA");

        LBL_ELEITORAL.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_ELEITORAL.setText("ELEITORAL");

        BTN_1.setBackground(new java.awt.Color(0, 0, 0));
        BTN_1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_1.setForeground(new java.awt.Color(255, 255, 255));
        BTN_1.setText("1");
        BTN_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_1ActionPerformed(evt);
            }
        });

        BTN_2.setBackground(new java.awt.Color(0, 0, 0));
        BTN_2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_2.setForeground(new java.awt.Color(255, 255, 255));
        BTN_2.setText("2");
        BTN_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_2ActionPerformed(evt);
            }
        });

        BTN_3.setBackground(new java.awt.Color(0, 0, 0));
        BTN_3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_3.setForeground(new java.awt.Color(255, 255, 255));
        BTN_3.setText("3");
        BTN_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_3ActionPerformed(evt);
            }
        });

        BTN_6.setBackground(new java.awt.Color(0, 0, 0));
        BTN_6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_6.setForeground(new java.awt.Color(255, 255, 255));
        BTN_6.setText("6");
        BTN_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_6ActionPerformed(evt);
            }
        });

        BTN_5.setBackground(new java.awt.Color(0, 0, 0));
        BTN_5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_5.setForeground(new java.awt.Color(255, 255, 255));
        BTN_5.setText("5");
        BTN_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_5ActionPerformed(evt);
            }
        });

        BTN_4.setBackground(new java.awt.Color(0, 0, 0));
        BTN_4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_4.setForeground(new java.awt.Color(255, 255, 255));
        BTN_4.setText("4");
        BTN_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_4ActionPerformed(evt);
            }
        });

        BTN_9.setBackground(new java.awt.Color(0, 0, 0));
        BTN_9.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_9.setForeground(new java.awt.Color(255, 255, 255));
        BTN_9.setText("9");
        BTN_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_9ActionPerformed(evt);
            }
        });

        BTN_8.setBackground(new java.awt.Color(0, 0, 0));
        BTN_8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_8.setForeground(new java.awt.Color(255, 255, 255));
        BTN_8.setText("8");
        BTN_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_8ActionPerformed(evt);
            }
        });

        BTN_7.setBackground(new java.awt.Color(0, 0, 0));
        BTN_7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_7.setForeground(new java.awt.Color(255, 255, 255));
        BTN_7.setText("7");
        BTN_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_7ActionPerformed(evt);
            }
        });

        BTN_0.setBackground(new java.awt.Color(0, 0, 0));
        BTN_0.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        BTN_0.setForeground(new java.awt.Color(255, 255, 255));
        BTN_0.setText("0");
        BTN_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_0ActionPerformed(evt);
            }
        });

        BTN_BRANCO.setBackground(new java.awt.Color(255, 255, 255));
        BTN_BRANCO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_BRANCO.setText("BRANCO");
        BTN_BRANCO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BRANCOActionPerformed(evt);
            }
        });

        BTN_CORRIGE.setBackground(new java.awt.Color(255, 51, 0));
        BTN_CORRIGE.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_CORRIGE.setText("CORRIGE");
        BTN_CORRIGE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CORRIGEActionPerformed(evt);
            }
        });

        BTN_CONFIRMA.setBackground(new java.awt.Color(0, 153, 0));
        BTN_CONFIRMA.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_CONFIRMA.setText("CONFIRMA");
        BTN_CONFIRMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CONFIRMAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PNL_DIGITOSLayout = new javax.swing.GroupLayout(PNL_DIGITOS);
        PNL_DIGITOS.setLayout(PNL_DIGITOSLayout);
        PNL_DIGITOSLayout.setHorizontalGroup(
            PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SPD_DIGITOS, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_DIGITOSLayout.createSequentialGroup()
                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LBL_BRASAO, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBL_ELEITORAL, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LBL_JUSTICA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                        .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BTN_1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                    .addComponent(BTN_4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BTN_7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BTN_2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTN_5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BTN_8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTN_0, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30)
                        .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(BTN_6, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addComponent(BTN_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(30, 30, 30))
            .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BTN_BRANCO)
                .addGap(18, 18, 18)
                .addComponent(BTN_CORRIGE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(BTN_CONFIRMA)
                .addContainerGap())
        );
        PNL_DIGITOSLayout.setVerticalGroup(
            PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(LBL_BRASAO, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(LBL_JUSTICA, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_ELEITORAL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SPD_DIGITOS, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BTN_0, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNL_DIGITOSLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(PNL_DIGITOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTN_BRANCO, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_CORRIGE, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BTN_CONFIRMA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PNL_TELA.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        LBL_FOTO.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LBL_FOTO.setMaximumSize(new java.awt.Dimension(110, 110));
        LBL_FOTO.setMinimumSize(new java.awt.Dimension(110, 110));

        LBL_SEUVOTO.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBL_SEUVOTO.setText("SEU VOTO PARA");

        LBL_CARGO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_CARGO.setText("DEPUTADO ESTADUAL (a)");

        LBL_NUMERO.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBL_NUMERO.setText("NÚMERO:");

        TXT_NUM1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N

        TXT_NUM2.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N

        TXT_NUM3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N

        TXT_NUM4.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N

        TXT_NUM5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N

        LBL_NOMECANDIDATO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        LBL_SIGLAPARTIDO.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        LBL_NOME.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        LBL_PARTIDO.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        LBL_APERTE.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBL_APERTE.setText("Aperte a tecla");

        LBL_VERDE.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_VERDE.setForeground(new java.awt.Color(0, 153, 0));
        LBL_VERDE.setText("VERDE");

        LBL_PARA1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBL_PARA1.setText("para");

        LBL_CONFIRMAR.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_CONFIRMAR.setForeground(new java.awt.Color(0, 153, 0));
        LBL_CONFIRMAR.setText("CONFIRMAR");

        LBL_LARANJA.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_LARANJA.setForeground(new java.awt.Color(255, 51, 0));
        LBL_LARANJA.setText("LARANJA");

        LBL_CORRIGIR.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        LBL_CORRIGIR.setForeground(new java.awt.Color(255, 51, 0));
        LBL_CORRIGIR.setText("CORRIGIR");

        LBL_PARA2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LBL_PARA2.setText("para");

        LBL_VICE.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        LBL_NOMEVICE.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        LBL_VOTOBRANCONULO.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        LBL_AVISONUMERO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout PNL_TELALayout = new javax.swing.GroupLayout(PNL_TELA);
        PNL_TELA.setLayout(PNL_TELALayout);
        PNL_TELALayout.setHorizontalGroup(
            PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_TELALayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LBL_VOTOBRANCONULO, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
            .addGroup(PNL_TELALayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNL_TELALayout.createSequentialGroup()
                        .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PNL_TELALayout.createSequentialGroup()
                                .addComponent(LBL_VERDE, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_PARA1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_CONFIRMAR, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PNL_TELALayout.createSequentialGroup()
                                .addComponent(LBL_LARANJA, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_PARA2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_CORRIGIR, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LBL_APERTE, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 365, Short.MAX_VALUE))
                    .addGroup(PNL_TELALayout.createSequentialGroup()
                        .addComponent(LBL_SEUVOTO, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 534, Short.MAX_VALUE))
                    .addGroup(PNL_TELALayout.createSequentialGroup()
                        .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PNL_TELALayout.createSequentialGroup()
                                .addComponent(LBL_PARTIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_SIGLAPARTIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PNL_TELALayout.createSequentialGroup()
                                .addComponent(LBL_NOME, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_NOMECANDIDATO, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PNL_TELALayout.createSequentialGroup()
                                .addComponent(LBL_VICE, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LBL_NOMEVICE, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(LBL_CARGO, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PNL_TELALayout.createSequentialGroup()
                                .addComponent(LBL_NUMERO)
                                .addGap(18, 18, 18)
                                .addComponent(TXT_NUM1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXT_NUM2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXT_NUM3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXT_NUM4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TXT_NUM5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBL_FOTO, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LBL_FOTOVICE, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95))))
            .addGroup(PNL_TELALayout.createSequentialGroup()
                .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PNL_TELALayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(SPD_CONFIRMACAO, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PNL_TELALayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(LBL_AVISONUMERO, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PNL_TELALayout.setVerticalGroup(
            PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_TELALayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LBL_SEUVOTO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PNL_TELALayout.createSequentialGroup()
                        .addComponent(LBL_CARGO)
                        .addGap(62, 62, 62)
                        .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LBL_NUMERO)
                            .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TXT_NUM1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXT_NUM2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXT_NUM3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXT_NUM4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TXT_NUM5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(LBL_FOTO, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_AVISONUMERO, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PNL_TELALayout.createSequentialGroup()
                        .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBL_NOMECANDIDATO, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LBL_NOME, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBL_SIGLAPARTIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LBL_PARTIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBL_NOMEVICE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LBL_VICE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(LBL_FOTOVICE, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBL_VOTOBRANCONULO, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SPD_CONFIRMACAO, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBL_APERTE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LBL_VERDE, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LBL_PARA1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LBL_CONFIRMAR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LBL_CORRIGIR, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PNL_TELALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LBL_LARANJA, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LBL_PARA2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout PNL_TOTALLayout = new javax.swing.GroupLayout(PNL_TOTAL);
        PNL_TOTAL.setLayout(PNL_TOTALLayout);
        PNL_TOTALLayout.setHorizontalGroup(
            PNL_TOTALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_TOTALLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PNL_TELA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(PNL_DIGITOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        PNL_TOTALLayout.setVerticalGroup(
            PNL_TOTALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNL_TOTALLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(PNL_TOTALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_TOTALLayout.createSequentialGroup()
                        .addComponent(PNL_TELA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNL_TOTALLayout.createSequentialGroup()
                        .addComponent(PNL_DIGITOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL_TOTAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PNL_TOTAL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_CONFIRMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CONFIRMAActionPerformed
        if (LBL_CARGO.getText().equals("DEPUTADO ESTADUAL (a)") && TXT_NUM5.getText().equals("")) {
            LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
        } else if (LBL_CARGO.getText().equals("DEPUTADO FEDERAL (a)") && TXT_NUM4.getText().equals("")) {
            LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
        } else if (LBL_CARGO.getText().equals("SENADOR (a)") && TXT_NUM3.getText().equals("")) {
            LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
        } else if (LBL_CARGO.getText().equals("GOVERNADOR (a)") && TXT_NUM2.getText().equals("")) {
            LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
        } else if (LBL_CARGO.getText().equals("PRESIDENTE (a)") && TXT_NUM2.getText().equals("")) {
            LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
        }

        //CONDIÇÕES PARA VOTO NULO OU EM BRANCO
        if (LBL_VOTOBRANCONULO.getText().equals("NÚMERO ERRADO") || LBL_VOTOBRANCONULO.getText().equals("VOTO EM BRANCO")) {
            if (LBL_CARGO.getText().equals("DEPUTADO ESTADUAL (a)")) {
                LBL_NUMERO.setText("NÚMERO:");
                LBL_VOTOBRANCONULO.setText("");
                LBL_CARGO.setText("DEPUTADO FEDERAL (a)");
                limpaTxtCandidato();
                limpaTela();
                audio();
                txtCandidatoVisible(LBL_CARGO.getText());
            } else if (LBL_CARGO.getText().equals("DEPUTADO FEDERAL (a)")) {
                LBL_NUMERO.setText("NÚMERO:");
                LBL_VOTOBRANCONULO.setText("");
                LBL_CARGO.setText("SENADOR (a)");
                limpaTxtCandidato();
                limpaTela();
                audio();
                txtCandidatoVisible(LBL_CARGO.getText());
            } else if (LBL_CARGO.getText().equals("SENADOR (a)")) {
                LBL_NUMERO.setText("NÚMERO:");
                LBL_VOTOBRANCONULO.setText("");
                LBL_CARGO.setText("GOVERNADOR (a)");
                limpaTxtCandidato();
                limpaTela();
                audio();
                txtCandidatoVisible(LBL_CARGO.getText());
            } else if (LBL_CARGO.getText().equals("GOVERNADOR (a)")) {
                LBL_NUMERO.setText("NÚMERO:");
                LBL_VOTOBRANCONULO.setText("");
                LBL_CARGO.setText("PRESIDENTE (a)");
                limpaTxtCandidato();
                limpaTela();
                audio();
                txtCandidatoVisible(LBL_CARGO.getText());
            } else if (LBL_CARGO.getText().equals("PRESIDENTE (a)")) {
                audio();
                Fim tela = new Fim();
                tela.setLocationRelativeTo(null);
                tela.show();
                dispose();
            }

            if (LBL_CARGO.getText().equals("DEPUTADO ESTADUAL (a)") && LBL_VOTOBRANCONULO.getText().equals("")) {
                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText()
                        + TXT_NUM4.getText() + TXT_NUM5.getText();
                numCand = Integer.parseInt(nCandidato);

                String sql = "update deputado_estadual set EST_TOTAL = (EST_TOTAL + 1) where EST_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setInt(1, numCand);
                    comando.executeUpdate();

                    comando.close();
                    conexao.close();

                    audio();
                    LBL_CARGO.setText("DEPUTADO FEDERAL (a)");
                    limpaTxtCandidato();
                    limpaTela();
                    txtCandidatoVisible(LBL_CARGO.getText());
                } catch (SQLException erro) {
                    LBL_VOTOBRANCONULO.setText("NÚMERO ERRADO");
                    erro.printStackTrace();
                }
            } else if (LBL_CARGO.getText().equals("DEPUTADO FEDERAL (a)") && LBL_VOTOBRANCONULO.getText().equals("")) {
                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText()
                        + TXT_NUM4.getText();
                numCand = Integer.parseInt(nCandidato);

                String sql = "update deputado_federal set FED_TOTAL = (FED_TOTAL + 1) where FED_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setInt(1, numCand);
                    comando.executeUpdate();

                    comando.close();
                    conexao.close();

                    audio();
                    LBL_CARGO.setText("SENADOR (a)");
                    limpaTxtCandidato();
                    limpaTela();
                    txtCandidatoVisible(LBL_CARGO.getText());
                } catch (SQLException erro) {
                    erro.printStackTrace();
                }
            } else if (LBL_CARGO.getText().equals("SENADOR (a)") && LBL_VOTOBRANCONULO.getText().equals("")) {
                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText() + TXT_NUM3.getText();
                numCand = Integer.parseInt(nCandidato);

                String sql = "update senador set SEN_TOTAL = (SEN_TOTAL + 1) where SEN_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setInt(1, numCand);
                    comando.executeUpdate();

                    comando.close();
                    conexao.close();

                    audio();
                    LBL_CARGO.setText("GOVERNADOR (a)");
                    limpaTxtCandidato();
                    limpaTela();
                    txtCandidatoVisible(LBL_CARGO.getText());
                } catch (SQLException erro) {
                    erro.printStackTrace();
                }
            } else if (LBL_CARGO.getText().equals("GOVERNADOR (a)") && LBL_VOTOBRANCONULO.getText().equals("")) {
                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText();
                numCand = Integer.parseInt(nCandidato);

                String sql = "update governador set GOV_TOTAL = (GOV_TOTAL + 1)  where GOV_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setInt(1, numCand);
                    comando.executeUpdate();

                    comando.close();
                    conexao.close();

                    audio();
                    LBL_CARGO.setText("PRESIDENTE (a)");
                    limpaTxtCandidato();
                    limpaTela();
                    txtCandidatoVisible(LBL_CARGO.getText());
                } catch (SQLException erro) {
                    erro.printStackTrace();
                }
            } else if (LBL_CARGO.getText().equals("PRESIDENTE (a)") && LBL_VOTOBRANCONULO.getText().equals("")) {
                nCandidato = TXT_NUM1.getText() + TXT_NUM2.getText();
                numCand = Integer.parseInt(nCandidato);

                String sql = "update presidente set PRE_TOTAL = (PRE_TOTAL + 1)  where PRE_NUMERO = ?";

                String url = "jdbc:mysql://127.0.0.1:3306/eleicao";
                String user = "root";
                String senha = "shieldcorrupted";

                try {
                    Connection conexao = DriverManager.getConnection(url, user, senha);

                    PreparedStatement comando = conexao.prepareStatement(sql);

                    comando.setInt(1, numCand);
                    comando.executeUpdate();

                    comando.close();
                    conexao.close();

                    limpaTela();
                    audio();
                    TXT_NUM1.setVisible(false);
                    TXT_NUM2.setVisible(false);
                    Fim tela = new Fim();
                    tela.setLocationRelativeTo(null);
                    tela.show();
                    dispose();
                } catch (SQLException erro) {
                    Fim tela = new Fim();
                    tela.setLocationRelativeTo(null);
                    tela.show();
                    dispose();
                    erro.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_BTN_CONFIRMAActionPerformed

    private void BTN_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_1ActionPerformed
        digito = "1";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_1ActionPerformed

    private void BTN_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_2ActionPerformed
        digito = "2";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_2ActionPerformed

    private void BTN_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_3ActionPerformed
        digito = "3";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_3ActionPerformed

    private void BTN_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_4ActionPerformed
        digito = "4";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_4ActionPerformed

    private void BTN_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_5ActionPerformed
        digito = "5";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_5ActionPerformed

    private void BTN_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_6ActionPerformed
        digito = "6";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_6ActionPerformed

    private void BTN_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_7ActionPerformed
        digito = "7";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_7ActionPerformed

    private void BTN_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_8ActionPerformed
        digito = "8";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_8ActionPerformed

    private void BTN_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_9ActionPerformed
        digito = "9";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_9ActionPerformed

    private void BTN_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_0ActionPerformed
        digito = "0";
        conexaoValidacaoCandidato(digito);
    }//GEN-LAST:event_BTN_0ActionPerformed

    private void BTN_CORRIGEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CORRIGEActionPerformed
        LBL_NUMERO.setText("NÚMERO:");
        LBL_VOTOBRANCONULO.setText(null);

        txtCandidatoVisible(LBL_CARGO.getText());
        limpaTxtCandidato();
        limpaTela();
    }//GEN-LAST:event_BTN_CORRIGEActionPerformed

    private void BTN_BRANCOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BRANCOActionPerformed
        limpaTxtCandidato();

        TXT_NUM1.setVisible(false);
        TXT_NUM2.setVisible(false);
        TXT_NUM3.setVisible(false);
        TXT_NUM4.setVisible(false);
        TXT_NUM5.setVisible(false);

        LBL_NUMERO.setText(null);
        limpaTela();
        LBL_VOTOBRANCONULO.setText("VOTO EM BRANCO");
    }//GEN-LAST:event_BTN_BRANCOActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaVotacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaVotacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_0;
    private javax.swing.JButton BTN_1;
    private javax.swing.JButton BTN_2;
    private javax.swing.JButton BTN_3;
    private javax.swing.JButton BTN_4;
    private javax.swing.JButton BTN_5;
    private javax.swing.JButton BTN_6;
    private javax.swing.JButton BTN_7;
    private javax.swing.JButton BTN_8;
    private javax.swing.JButton BTN_9;
    private javax.swing.JButton BTN_BRANCO;
    private javax.swing.JButton BTN_CONFIRMA;
    private javax.swing.JButton BTN_CORRIGE;
    private javax.swing.JLabel LBL_APERTE;
    private javax.swing.JLabel LBL_AVISONUMERO;
    private javax.swing.JLabel LBL_BRASAO;
    private javax.swing.JLabel LBL_CARGO;
    private javax.swing.JLabel LBL_CONFIRMAR;
    private javax.swing.JLabel LBL_CORRIGIR;
    private javax.swing.JLabel LBL_ELEITORAL;
    private javax.swing.JLabel LBL_FOTO;
    private javax.swing.JLabel LBL_FOTOVICE;
    private javax.swing.JLabel LBL_JUSTICA;
    private javax.swing.JLabel LBL_LARANJA;
    private javax.swing.JLabel LBL_NOME;
    private javax.swing.JLabel LBL_NOMECANDIDATO;
    private javax.swing.JLabel LBL_NOMEVICE;
    private javax.swing.JLabel LBL_NUMERO;
    private javax.swing.JLabel LBL_PARA1;
    private javax.swing.JLabel LBL_PARA2;
    private javax.swing.JLabel LBL_PARTIDO;
    private javax.swing.JLabel LBL_SEUVOTO;
    private javax.swing.JLabel LBL_SIGLAPARTIDO;
    private javax.swing.JLabel LBL_VERDE;
    private javax.swing.JLabel LBL_VICE;
    private javax.swing.JLabel LBL_VOTOBRANCONULO;
    private javax.swing.JPanel PNL_DIGITOS;
    private javax.swing.JPanel PNL_TELA;
    private javax.swing.JPanel PNL_TOTAL;
    private javax.swing.JSeparator SPD_CONFIRMACAO;
    private javax.swing.JSeparator SPD_DIGITOS;
    private javax.swing.JTextField TXT_NUM1;
    private javax.swing.JTextField TXT_NUM2;
    private javax.swing.JTextField TXT_NUM3;
    private javax.swing.JTextField TXT_NUM4;
    private javax.swing.JTextField TXT_NUM5;
    // End of variables declaration//GEN-END:variables
}
