package clue;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Clue extends JFrame {

    int turno_jugador = 0;
    int partidas_jugadas = 0;
    Opera mazo = new Opera();
    String[] numero_de_jugadores = {"2", "3", "4",};
    int jugadores = JOptionPane.showOptionDialog(null, "Elegir cantidad de jugadores", "Bienvenido a Clue", JOptionPane.YES_OPTION, JOptionPane.DEFAULT_OPTION, null, numero_de_jugadores, NORMAL) + 2;
    Jugador jugador[] = new Jugador[jugadores];
    JFrame newPanel[] = new JFrame[jugadores];
    int jugadores_activos = jugadores;
    Jugador misterio = new Jugador();
    JTable table[] = new JTable[jugadores];
    ImageIcon icon1 = new ImageIcon("src/resources/Tablero.jpeg");
    Icon tableroIcon = new ImageIcon(icon1.getImage().getScaledInstance(640, 640, Image.SCALE_SMOOTH));
    JLabel tablero = new JLabel(tableroIcon);
    String[] lugares_str = {"Sala", "Patio", "Vestíbulo", "Habitación de huéspedes", "Comedor", "Teatro", "Cocina", "Spa", "Observatorio"};
    JLabel lugares_label = new JLabel("Lugares");
    JComboBox lugares = new JComboBox(lugares_str);
    JButton Notas = new JButton("Notas");
    JLabel armas_label = new JLabel("Armas");
    String[] armas_str = {"Bate", "Cuchillo", "Pistola", "Veneno", "Hacha", "Pesas", "Candelabro", "Trofeo", "Cuerda"};
    JComboBox armas = new JComboBox(armas_str);
    JLabel personajes_label = new JLabel("Personajes");
    String[] personajes_str = {"Srita Escarlata", "Entrenador Mostaza", "Sra Blanco", "Sr Verdi", "Profesor Moradillo", "Sra Azulino"};
    JComboBox personajes = new JComboBox(personajes_str);
    JScrollPane scrollPane[] = new JScrollPane[jugadores];
    JPanel middlePanel = new JPanel();
    JPanel DerecharPanel = new JPanel();
    JButton Suposición = new JButton("Suposición");
    JPanel Acciones = new JPanel();
    JButton Acusar = new JButton("Acusar");
    JPanel InfoPanel = new JPanel();
    JButton Siguiente_turno = new JButton("Siguiente Turno");
    String elegido;
    JLabel nombre_jugador = new JLabel("Nombre de jugador");
    JLabel avatar_label = new JLabel();
    JButton ver_cartas = new JButton("Ver mis cartas");
    JButton reiniciar_partida = new JButton("Reiniciar la partida");
    JLabel numero_partidas_jugadas = new JLabel("Se han jugado " + partidas_jugadas + " partidas");
    JLabel numero_partidas_ganadas = new JLabel();
    JButton generar_numero = new JButton("Generar número para sala");

    Clue() {
        super("Clue");
        Container cont = new Container();
        agregar_cartas_al_mazo();
        numero_partidas_jugadas.setFont(numero_partidas_jugadas.getFont().deriveFont(15f));
        crear_usuarios(jugador, newPanel, scrollPane, table, mazo);
        cont = getContentPane();
        cont.setLayout(new FlowLayout());
        cont.setLayout(new BorderLayout(8, 6));
        middlePanel.setLayout(new FlowLayout(30, 30, 30));
        cont.add(middlePanel, BorderLayout.WEST);
        middlePanel.add(tablero);
        Siguiente_turno.setEnabled(false);
        InfoPanel.setBorder(new LineBorder(Color.BLACK, 1));
        InfoPanel.setLayout(new FlowLayout());
        numero_partidas_ganadas.setText("Jugador " + jugador[turno_jugador].nombre + " ha ganado " + jugador[turno_jugador].partidas_ganadas + " veces");
        numero_partidas_ganadas.setFont(numero_partidas_ganadas.getFont().deriveFont(15f));
        DerecharPanel.add(numero_partidas_jugadas);
        DerecharPanel.add(numero_partidas_ganadas);
        Suposición.setEnabled(false);
        DerecharPanel.add(avatar_label);
        DerecharPanel.add(InfoPanel);
        generar_numero.addActionListener(new control());
        DerecharPanel.add(generar_numero);
        Siguiente_turno.addActionListener(new control());
        DerecharPanel.add(Siguiente_turno);
        InfoPanel.add(Notas);
        reiniciar_partida.addActionListener(new control());
        InfoPanel.add(reiniciar_partida);
        ver_cartas.addActionListener(new control());
        InfoPanel.add(ver_cartas);
        DerecharPanel.setBorder(new LineBorder(Color.BLACK, 2));
        DerecharPanel.setLayout(new FlowLayout(30, 30, 30));
        cont.add(DerecharPanel, BorderLayout.CENTER);
        Acciones.setBorder(new LineBorder(Color.BLACK, 1));
        Acciones.setLayout(new GridLayout(4, 1, 5, 5));
        DerecharPanel.add(Acciones);
        Acciones.add(lugares_label);
        Acciones.add(lugares);
        Acciones.add(armas_label);
        Acciones.add(armas);
        Acciones.add(personajes_label);
        Acciones.add(personajes);
        Suposición.addActionListener(new control());
        Acciones.add(Suposición);
        Acusar.addActionListener(new control());
        Acciones.add(Acusar);
        Notas.addActionListener(new control());
        setSize(1200, 720);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        nombre_jugador.setText(jugador[0].nombre);
        avatar_label.setIcon(jugador[0].avatar);
    }

    public static void main(String[] args) {
        Clue app = new Clue();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void agregar_cartas_al_mazo() {
        Random generar_cartas = new Random();
        mazo.insertar_al_final(0, "Bate", "src/resources/Imgenes/Arma/bate.jpg");
        mazo.insertar_al_final(1, "Candelabro", "src/resources/Imgenes/Arma/candelabro.jpg");
        mazo.insertar_al_final(2, "Cuchillo", "src/resources/Imgenes/Arma/cuchillo.jpg");
        mazo.insertar_al_final(3, "Cuerda", "src/resources/Imgenes/Arma/cuerda.jpg");
        mazo.insertar_al_final(4, "Hacha", "src/resources/Imgenes/Arma/hacha.jpg");
        mazo.insertar_al_final(5, "Pesas", "src/resources/Imgenes/Arma/pesa.jpg");
        mazo.insertar_al_final(6, "Pistola", "src/resources/Imgenes/Arma/pistola.jpg");
        mazo.insertar_al_final(7, "Trofeo", "src/resources/Imgenes/Arma/trofeo.jpg");
        mazo.insertar_al_final(8, "Veneno", "src/resources/Imgenes/Arma/veneno.jpg");

        mazo.insertar_al_final(9, "Cocina", "src/resources/Imgenes/Habitacion/cocina.jpg");
        mazo.insertar_al_final(10, "Patio", "src/resources/Imgenes/Habitacion/Patio.jpg");
        mazo.insertar_al_final(11, "Comedor", "src/resources/Imgenes/Habitacion/comedor.jpg");
        mazo.insertar_al_final(12, "Habitación de huéspedes", "src/resources/Imgenes/Habitacion/huespedes.jpg");
        mazo.insertar_al_final(13, "Observatorio", "src/resources/Imgenes/Habitacion/observatorio.jpg");
        mazo.insertar_al_final(14, "Sala", "src/resources/Imgenes/Habitacion/sala.jpg");
        mazo.insertar_al_final(15, "Spa", "src/resources/Imgenes/Habitacion/spa.jpg");
        mazo.insertar_al_final(16, "Teatro", "src/resources/Imgenes/Habitacion/teatro.jpg");
        mazo.insertar_al_final(17, "vestíbulo", "src/resources/Imgenes/Habitacion/vestibulo.jpg");

        mazo.insertar_al_final(18, "Entrenador Mostaza", "src/resources/Imgenes/Sospechoso/entmostaza.jpg");
        mazo.insertar_al_final(19, "Profesor Moradillo", "src/resources/Imgenes/Sospechoso/prfmoradillo.jpg");
        mazo.insertar_al_final(20, "Sra Azulino", "src/resources/Imgenes/Sospechoso/srtazulino.jpg");
        mazo.insertar_al_final(21, "Sra Blanco", "src/resources/Imgenes/Sospechoso/srtblanco.jpg");
        mazo.insertar_al_final(22, "Srita Escarlata", "src/resources/Imgenes/Sospechoso/srtescarlata.jpg");
        mazo.insertar_al_final(23, "Sr Verdi", "src/resources/Imgenes/Sospechoso/srverdi.jpg");
        misterio = new Jugador();
        misterio.cartas_recibidas = 3;
        misterio.cartas = new int[misterio.cartas_recibidas];
        misterio.cartas[0] = generar_cartas.nextInt(8);
        misterio.cartas[1] = generar_cartas.nextInt(8) + 9;
        misterio.cartas[2] = generar_cartas.nextInt(6) + 18;
        misterio.mis_cartas = new Opera();
        misterio.mis_cartas.asignar_cartas(misterio.cartas[0], mazo.Lista);
        mazo.borrar(misterio.cartas[0]);
        misterio.mis_cartas.asignar_cartas(misterio.cartas[1], mazo.Lista);
        mazo.borrar(misterio.cartas[1]);
        misterio.mis_cartas.asignar_cartas(misterio.cartas[2], mazo.Lista);
        mazo.borrar(misterio.cartas[2]);
        mazo.reacomodar();
        //misterio.mis_cartas.ver_listafinprinima();
    }

    public void crear_usuarios(Jugador jugador[], JFrame newPanel[], JScrollPane scrollPane[], JTable table[], Opera mazo) {
        Random generar_cartas = new Random();
        int numero_carta = 0;
        try {
            String[] avatar = {"Chunky Kong", "Dixie Kong", "Donkey Kong", "Funky Kong"};
            ImageIcon icon0[] = new ImageIcon[4];
            icon0[0] = new ImageIcon("src/resources/Imgenes/Avatar/Chunky Kong.jpeg");
            icon0[1] = new ImageIcon("src/resources/Imgenes/Avatar/Dixie Kong.jpeg");
            icon0[2] = new ImageIcon("src/resources/Imgenes/Avatar/Donkey Kong.jpeg");
            icon0[3] = new ImageIcon("src/resources/Imgenes/Avatar/Funky Kong.jpeg");
            for (int i = 0; i < jugadores; i++) {
                jugador[i] = new Jugador();
                newPanel[i] = new JFrame();
                jugador[i].mis_cartas = new Opera();
                table[i] = new JTable(new MyTableModel());
                scrollPane[i] = new JScrollPane(table[i]);
                jugador[i].nombre = JOptionPane.showInputDialog(null, "Digite el nombre del jugador número " + (i + 1));
                while (jugador[i].nombre.isEmpty() == true || jugador[i].nombre.equals(" ") != false || jugador[i].nombre.equals("  ") != false) {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario no puede estar vacío");
                    jugador[i].nombre = JOptionPane.showInputDialog(null, "Digite el nombre del jugador número " + (i + 1));
                }
                switch (JOptionPane.showOptionDialog(null, icon0, "Elegir avatar (Donkey Kong se aplica por defecto)", JOptionPane.YES_OPTION, JOptionPane.DEFAULT_OPTION, null, avatar, NORMAL)) {
                    case 0:
                        jugador[i].avatar = icon0[0];
                        break;
                    case 1:
                        jugador[i].avatar = icon0[1];
                        break;
                    case 2:
                        jugador[i].avatar = icon0[2];
                        break;
                    case 3:
                        jugador[i].avatar = icon0[3];
                        break;
                    case 4:
                        jugador[i].avatar = icon0[4];
                        break;
                }
                jugador[i].newPanel = newPanel[i];
                jugador[i].newPanel.add(scrollPane[i]);
                jugador[i].newPanel.setSize(500, 500);
                jugador[i].newPanel.setLocationRelativeTo(null);
                jugador[i].newPanel.setVisible(false);
                jugador[i].newPanel.setTitle("Tabla de sospechas del jugador " + jugador[i].nombre);
                jugador[i].numero_jugador = i + 1;

            }

            while (mazo.numero() != 0) {
                for (int i = 0; i < jugador.length; i++) {
                    if (mazo.numero() == 0) {
                        break;
                    } else {
                        numero_carta = generar_cartas.nextInt(mazo.numero());
                        jugador[i].mis_cartas.asignar_cartas(numero_carta, mazo.Lista);
                        jugador[i].cartas_recibidas++;
                        mazo.borrar(numero_carta);
                        mazo.reacomodar();
                    }

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrar nombre " + e);
            crear_usuarios(jugador, newPanel, scrollPane, table, mazo);
        }

    }

    public void reiniciar_partida() {
        Random generar_cartas = new Random();
        int numero_carta = 0;
        mazo = new Opera();
        agregar_cartas_al_mazo();
        for (int i = 0; i < jugadores; i++) {
            newPanel[i] = new JFrame();
            jugador[i].mis_cartas = new Opera();
            jugador[i].activo = true;
            table[i] = new JTable(new MyTableModel());
            scrollPane[i] = new JScrollPane(table[i]);
            jugador[i].newPanel = newPanel[i];
            jugador[i].newPanel.add(scrollPane[i]);
            jugador[i].newPanel.setSize(500, 500);
            jugador[i].newPanel.setLocationRelativeTo(null);
            jugador[i].newPanel.setVisible(false);
            jugador[i].newPanel.setTitle("Tabla de sospechas del jugador " + jugador[i].nombre);
            jugador[i].numero_jugador = i + 1;
        }
        while (mazo.numero() != 0) {
            for (int i = 0; i < jugador.length; i++) {
                if (mazo.numero() == 0) {
                    break;
                } else {
                    numero_carta = generar_cartas.nextInt(mazo.numero());
                    jugador[i].mis_cartas.asignar_cartas(numero_carta, mazo.Lista);
                    jugador[i].cartas_recibidas++;
                    mazo.borrar(numero_carta);
                    mazo.reacomodar();
                }

            }

        }
        partidas_jugadas++;
        numero_partidas_jugadas.setText("Se han jugado " + partidas_jugadas + " partida(s)");
        numero_partidas_jugadas.setFont(numero_partidas_jugadas.getFont().deriveFont(15f));
        numero_partidas_ganadas.setText("Jugador " + jugador[0].nombre + " ha ganado " + jugador[0].partidas_ganadas + " veces");
        numero_partidas_ganadas.setFont(numero_partidas_ganadas.getFont().deriveFont(15f));
        Siguiente_turno.setEnabled(false);
        generar_numero.setEnabled(true);
        Suposición.setEnabled(false);
    }

    public void manejo_turnos() {
        if (turno_jugador >= jugador.length - 1) {
            turno_jugador = 0;
        } else {
            turno_jugador++;
        }
        nombre_jugador.setText(jugador[turno_jugador].nombre);
        avatar_label.setIcon(jugador[turno_jugador].avatar);

    }

    public void Lugar_para_Suposición() {
        Random MiAleatorio = new Random();
        int n = 1 + MiAleatorio.nextInt(6);
        int y = 1 + MiAleatorio.nextInt(6);
        int suma = (n + y);
        Object[] j1_2_3 = {"Spa ", "Patio ", "Cocina"};
        Object[] j1_4_5 = {"Teatro ", "Comedor"};
        Object[] j1_6_7 = {"Sala ", "Vestibulo"};
        Object[] j1_8_9 = {"Observatorio ", "Hab.Huespedes"};
        Object[] j1_10_12 = {"Vestibulo ", "Cocina"};

        switch (suma) {
            case 2:
            case 3:
                elegido = (String) JOptionPane.showInputDialog(null, "Su cifra es de " + suma + ", Por favor elija la sala a la que desea entrar ", "Salas Disponibles", JOptionPane.WARNING_MESSAGE, null, j1_2_3, null);
                break;
            case 4:
            case 5:
                elegido = (String) JOptionPane.showInputDialog(null, "Su cifra es de " + suma + ", Por favor elija la sala a la que desea entrar ", "Salas Disponibles", JOptionPane.WARNING_MESSAGE, null, j1_4_5, null);
                break;
            case 6:
            case 7:
                elegido = (String) JOptionPane.showInputDialog(null, "Su cifra es de " + suma + ", Por favor elija la sala a la que desea entrar ", "Salas Disponibles", JOptionPane.WARNING_MESSAGE, null, j1_6_7, null);
                break;
            case 8:
            case 9:
                elegido = (String) JOptionPane.showInputDialog(null, "Su cifra es de " + suma + ", Por favor elija la sala a la que desea entrar ", "Salas Disponibles", JOptionPane.WARNING_MESSAGE, null, j1_8_9, null);
                break;
            case 10:
            case 12:
                elegido = (String) JOptionPane.showInputDialog(null, "Su cifra es de " + suma + ", Por favor elija la sala a la que desea entrar ", "Salas Disponibles", JOptionPane.WARNING_MESSAGE, null, j1_10_12, null);
                break;
            default:
                break;
        }
    }

    public class control implements ActionListener {

        public void actionPerformed(ActionEvent evento) {
            if (evento.getSource() == Siguiente_turno) {
                manejo_turnos();
                Suposición.setEnabled(false);
                numero_partidas_ganadas.setText("Jugador " + jugador[turno_jugador].nombre + " ha ganado " + jugador[turno_jugador].partidas_ganadas + " veces");
                numero_partidas_ganadas.setFont(numero_partidas_ganadas.getFont().deriveFont(15f));
                if (jugador[turno_jugador].activo == true) {
                    Acusar.setEnabled(true);
                    Siguiente_turno.setEnabled(false);
                    generar_numero.setEnabled(true);
                } else {
                    Acusar.setEnabled(false);
                    Siguiente_turno.setEnabled(true);
                    generar_numero.setEnabled(false);
                }
            }
            if (evento.getSource() == Notas) {
                jugador[turno_jugador].newPanel.setVisible(true);
            }
            if (evento.getSource() == ver_cartas) {
                jugador[turno_jugador].mis_cartas.ver_listafinprinima();
            }
            if (evento.getSource() == Acusar) {
                String[] opciones1 = {"Si", "No",};
                int decisión1 = JOptionPane.showOptionDialog(null, "¿Está seguro que desea hacer una acusación ? ", "Acusación", JOptionPane.YES_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones1, NORMAL);

                if (decisión1 == 0) {
                    int cont = 0;
                    String cartas_elegidas[] = new String[3];
                    cartas_elegidas[0] = lugares.getSelectedItem().toString();
                    cartas_elegidas[1] = armas.getSelectedItem().toString();
                    cartas_elegidas[2] = personajes.getSelectedItem().toString();
                    for (int i = 0; i < cartas_elegidas.length; i++) {
                        if (misterio.mis_cartas.buscar(cartas_elegidas[i]) == true) {
                            cont++;
                        }
                    }

                    if (cont == 3) {
                        String[] opciones = {"Si", "No, cerrar el juego",};

                        JOptionPane.showMessageDialog(null, "Felicidades, lograste adivinar");
                        jugador[turno_jugador].partidas_ganadas++;
                        numero_partidas_ganadas.setText("Jugador " + jugador[turno_jugador].nombre + " ha ganado " + jugador[turno_jugador].partidas_ganadas + " veces");
                        numero_partidas_ganadas.setFont(numero_partidas_ganadas.getFont().deriveFont(15f));
                        int decisión = JOptionPane.showOptionDialog(null, "¿Jugar otra partida? ", "Fin de juego", JOptionPane.YES_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones, NORMAL);

                        if (decisión == 0) {
                            reiniciar_partida.doClick();
                        } else {
                            System.exit(cont);
                        }

                    } else {
                        jugador[turno_jugador].activo = false;
                        Acusar.setEnabled(false);
                        JOptionPane.showMessageDialog(null, "No lograste adivinar, perdiste");
                        generar_numero.setEnabled(false);
                        jugadores_activos--;
                    }
                    if (jugadores_activos == 0) {
                        String[] opciones = {"Si", "No, cerrar el juego",};
                        JOptionPane.showMessageDialog(null, "Nadie logró adivinar, fin de juego");
                        int decisión = JOptionPane.showOptionDialog(null, "¿Jugar otra partida? ", "Fin de juego", JOptionPane.YES_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones, NORMAL);

                        if (decisión == 0) {
                            reiniciar_partida.doClick();
                        } else {
                            System.exit(cont);
                        }
                    }
                    Siguiente_turno.setEnabled(true);
                }

            }

            if (evento.getSource() == reiniciar_partida) {
                String[] opciones = {"Si", "No",};
                int decisión = JOptionPane.showOptionDialog(null, "¿Está seguro que desea reiniciar la partida? ", "Reiniciar", JOptionPane.YES_OPTION, JOptionPane.DEFAULT_OPTION, null, opciones, NORMAL);
                if (decisión == 0) {
                    reiniciar_partida();
                    turno_jugador = 0;
                    JOptionPane.showMessageDialog(null, "Partida reiniciada");
                }
            }

            if (evento.getSource() == Suposición) {
                JOptionPane.showMessageDialog(null, "En este momento puede proceder a hacer una suposición verbalmente basado en el lugar " + elegido + ". Use sus notas para hacer apuntes de acuerdo a las respuestas de los demás");
                Siguiente_turno.setEnabled(true);
            }

            if (evento.getSource() == generar_numero) {
                Lugar_para_Suposición();
                Suposición.setEnabled(true);
                generar_numero.setEnabled(false);
            }
        }

    }
}
