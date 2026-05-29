// author : Paschalis Koukos
// This class represents a topic with a name and description.
// It provides a constructor to initialize these attributes and getter methods to retrieve their values.
// helper class used in our web service to store the topics.
package org.hua.it2024041;

public class Topic {
    public String name;
    public String description;

    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getter methods for name and description
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    
}