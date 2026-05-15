package com.petshelter.petsheltersystem;

public abstract class Pet {

    private static int idCounter = 1; //ID of Pet

    protected String id;
    protected String name;
    protected int age;
    protected Gender gender;
    protected PetStatus status;
    protected String breedOrDescription;

    public Pet(String name, int age, Gender gender, String breedOrDescription) throws InvalidPetException {
        this.id = "PET-" + (idCounter++); //Increases once may madagdag
        setName(name);
        setAge(age);
        this.gender = gender;
        this.breedOrDescription = breedOrDescription;
        this.status = PetStatus.AVAILABLE;
    }

    // Validation of pet names
    private void setName(String name) throws InvalidPetException {
        if (name == null || !name.matches("^[A-Za-z\\s]{2,30}$")) {
            throw new InvalidPetException("Name must be 2-30 letters and spaces only.");
        }
        this.name = name.trim();
    }

    // Ivavalidate yung age ng pets
    private void setAge(int age) throws InvalidPetException {
        if (age < 0 || age > 30) {
            throw new InvalidPetException("Age must be between 0 and 30 years.");
        }
        this.age = age;
    }

    // Getters
    public String getId() { 
        return id; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public int getAge() { 
        return age; 
    }
    
    public Gender getGender() { 
        return gender; 
    }
    
    public PetStatus getStatus() { 
        return status; 
    }
    
    public String getBreedOrDescription() { 
        return breedOrDescription; 
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + age + " years | " + 
               gender + " | " + breedOrDescription + " | Status: " + status;
    }
}