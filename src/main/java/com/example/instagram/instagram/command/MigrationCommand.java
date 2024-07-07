package com.example.instagram.instagram.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MigrationCommand {
    
    @ShellMethod(key = "migration:create", value = "Create migration template")
    public void createMigrationTemplate(@ShellOption(defaultValue = ShellOption.NULL) String name) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        String filename = "V" + timestamp + "__create_" + name + "_table_.sql";
        
        // Step 2: Create a File object in the migrations directory
        File file = new File("src/main/resources/db/migration/" + filename);
        
        try {
            // Step 3: Write to the file
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            // Example SQL template content
            printWriter.println("-- Migration template for " + name);
            printWriter.println("CREATE TABLE " + name + " (\r\n" + //
                                "    id UUID PRIMARY KEY,\r\n" + //
                                "    createdAt TIMESTAMP WITHOUT TIME ZONE NOT NULL,\r\n" + //
                                "    createdBy VARCHAR(255) NOT NULL,\r\n" + //
                                "    updatedAt TIMESTAMP WITHOUT TIME ZONE NOT NULL,\r\n" + //
                                "    updatedBy VARCHAR(255) NOT NULL\r\n" + //
                                ");");
            printWriter.println("-- Add your SQL migration scripts here");
            
            // Step 4: Close the writers
            printWriter.close();
            fileWriter.close();
            
            System.out.println("Migration template created: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("An error occurred while creating the migration template: " + e.getMessage());
        }
    }

}
