/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validateTextField;

import com.jfoenix.controls.JFXTextField;

/**
 *
 * @author Víctor
 */
public class numberTextField extends JFXTextField{
    @Override
    public void replaceText(int i, int i1, String string){
        if(string.matches("[0-9]") || string.isEmpty()){
            super.replaceText(i, i1, string);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
