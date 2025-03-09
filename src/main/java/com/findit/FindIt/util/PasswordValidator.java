package com.findit.FindIt.util;

import com.findit.FindIt.exception.PasswordValidationException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordValidator {

    // Валидация пароля
    public String validatePassword(String password){
//        if(password.matches("[0-9]?")){
//            throw new PasswordValidationException("The password must contain at least one of the symbols 0-9");
//        } else if(password.matches("[a-z]?")){
//            throw new PasswordValidationException("The password must contain at least one of the symbols a-z");
//        } else if(password.matches("[A-Z]?")){
//            throw new PasswordValidationException("The password must contain at least one of the symbols A-Z");
//        } else if(password.matches("[!@#$%^&*()+=]?")){
//            throw new PasswordValidationException("The password must contain at least one of the special symbols !@#$%^&*()+=");
//        } else if(password.matches("\\S+")){
//            throw new PasswordValidationException("The password must not contain spaces");
//        } else if(!password.matches(".{8,}")){
//            throw new PasswordValidationException("The password must contain at least more than 8 symbols");
//        }

        if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            throw new PasswordValidationException("the password must contain at least one of the following symbols: 0-9, a-z, A-Z, !@#$%^&*()+=, no spaces; must contain more 8 symbols");
        }

        return password;
    }
}
