

public class Persona {
    private int id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private int eta;

    public Persona(String nome, String cognome, String indirizzo, String telefono, int eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.eta = eta;
    }

    public Persona(int id, String nome, String cognome, String indirizzo, String telefono, int eta) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.telefono = telefono;
        this.eta = eta;
    }

    public int getId() { 
        return this.id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getNome() { 
        return this.nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getCognome() { 
        return this.cognome; 
    }

    public void setCognome(String cognome) { 
        this.cognome = cognome; 
    }

    public String getIndirizzo() { 
        return this.indirizzo; 
    }
    public void setIndirizzo(String indirizzo) { 
        this.indirizzo = indirizzo; 
    }

    public String getTelefono() { 
        return this.telefono; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }

    public int getEta() { 
        return this.eta; 
    }
    public void setEta(int eta) { 
        this.eta = eta; 
    }

    @Override
    public String toString() {
        return this.nome + " " + this.cognome + " | " + this.indirizzo + " | Tel: " + this.telefono + " | Età: " + this.eta;
    }
}
