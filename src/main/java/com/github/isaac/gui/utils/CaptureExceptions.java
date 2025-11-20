package com.github.isaac.gui.utils;

import jakarta.validation.ConstraintViolationException;

import javax.swing.*;
import java.awt.*;

public class CaptureExceptions {
    public static void capture(Component parentComponent, Runnable action) {
        try {
            action.run();
        } catch (ConstraintViolationException ex) {
            StringBuilder errorMessage = new StringBuilder("Errores de validación:\n");

            ex.getConstraintViolations().forEach(violation ->
                    errorMessage.append("- ").append(violation.getMessage()).append("\n")
            );

            JOptionPane.showMessageDialog(parentComponent, errorMessage.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
