/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tratadoreventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Tarefa;
import view.Cartao;
import view.TarefaView;

/**
 *
 * @author danie
 */
public class TratadorDeEvento implements ActionListener{
    // uma referências para tarefa de onde veio a ação de clique
    private Tarefa tarefa;

    public TratadorDeEvento(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origemEvento = e.getClass();
        // abrir tarefa
        if(e.getActionCommand().equals(("Abrir Tarefa"))){
            var f = new TarefaView(tarefa);
            f.iniciarComponentes();
            f.construirPainelTarefa();
            f.setVisible(true);
        }
    }
    
}
