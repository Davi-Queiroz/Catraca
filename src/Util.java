import java.net.spi.URLStreamHandlerProvider;
import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Util {

    private BilheteUnico[] bilhete = new BilheteUnico[5];
    private int index = 0;

    public void menuPrincipal() {
        int opcao = 0;
        String menu = "1. Administrador\n2. Usuário\n3. Finalizar";

        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 3) {
                showMessageDialog(null, "Opção inválida");
            } else {
                switch (opcao) {
                    case 1:
                        menuAdministrador();
                        break;
                    case 2:
                        menuUsuario();
                        break;
                }
            }
        } while (opcao != 3);
    }

    private void menuUsuario() {
        int opcao;
        String menuUsuario = "1. Consultar Saldo\n";
        menuUsuario += "2. Caregar bilhete\n";
        menuUsuario += "3. Passar catraca\n";
        menuUsuario += "4. Sair";


        do {
            opcao = parseInt(showInputDialog(menuUsuario));
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválido");
            } else {
                switch (opcao) {
                    case 1:
                        consultarSaldo();
                        break;
                    case 2:
                        carregarBilhete();
                        break;
                    case 3:
                        passarCatraca();
                        break;

                }
            }
        }
        while (opcao != 4);

    }

    private void menuAdministrador() {
        int opcao;
        String menuAdmin = "1. Emitir bilhete\n";
        menuAdmin += "2. Listar bilhetes\n";
        menuAdmin += "3. Remover bilhetes\n";
        menuAdmin += "4. Sair\n";

        do {
            opcao = parseInt(showInputDialog(menuAdmin));
            if (opcao < 1 || opcao > 4) {
                showMessageDialog(null, "Opção inválido");
            } else {
                switch (opcao) {
                    case 1:
                        emitirBilhete();
                        break;
                    case 2:
                        listarBilhetes();
                        break;
                    case 4:
                }
            }
        } while (opcao != 4);
    }

    private void listarBilhetes() {
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for (int i = 0; i < index; i++) {
            aux += "Numero do bilhete: " + bilhete[i].numero + "\n";
            aux += "Nome do usuario: " + bilhete[i].usuario.nome + "\n";
            aux += "Saldo: R$" + df.format(bilhete[i].consultarSaldo()) + "\n";
            aux += "Tipo de tatifa: " + bilhete[i].usuario.tipoTarifa + "\n";
            aux += "============================\n";
        }
        showMessageDialog(null, aux);
    }

    private void emitirBilhete() {
        String nome;
        long cpf;
        String perfil;

        if (index < bilhete.length) {
            nome = showInputDialog("Nome:");
            cpf = parseLong(showInputDialog("CPF"));
            perfil = showInputDialog("Estudante ou Professor ou Comum");
            bilhete[index] = new BilheteUnico(new Usuario(nome, cpf, perfil));
            index++;
        }
    }

    private void consultarSaldo() {
        int posicao = pesquisarCPF();
        if (posicao != -1) {
            showMessageDialog(null, bilhete[posicao].consultarSaldo());
        }
    }

    private void carregarBilhete() {
        int posicao = pesquisarCPF();
        double valor;
        if (posicao != -1) {
            valor = parseDouble(showInputDialog("Valor da recarga"));
            bilhete[posicao].carregar(valor);
        }
    }

    private void passarCatraca() {
        int posicao = pesquisarCPF();
        double valor;
        if (posicao != -1) {
            bilhete[posicao].passarNaCatraca();
        }
    }

    private int pesquisarCPF() {
        Long cpf = parseLong(showInputDialog("CPF"));
        for (int i = 0; i < index; i++) {
            if (bilhete[i].usuario.cpf == cpf) {
                return i;
            }
        }
        showMessageDialog(null, cpf + "Nao encontrado");
        return -1;
    }
}

