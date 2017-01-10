/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioswingv2;

import controller.ApplicationController;
import java.awt.EventQueue;
import view.ApplicationView;

/**
 *
 * @author m_lig
 */
public class IOSWINGv2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ApplicationView view = new ApplicationView();
            ApplicationController controller = new ApplicationController(view);
            view.setVisible(true);
        });
    }
    
}
