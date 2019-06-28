package com.infnet.infnet.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String email;
    private String senha;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "USER_PRODUCT",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<Product>();

    public User() {
    }
    public User(String nome, String email, String senha) {
        this();
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
    }

    public User(String nome, String email) {
        this();
        this.setNome(nome);
        this.setEmail(email);
    }




    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getNome() != null ? !getNome().equals(user.getNome()) : user.getNome() != null) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        return getSenha().equals(user.getSenha());
    }

    @Override
    public int hashCode() {
        int result = getNome() != null ? getNome().hashCode() : 0;
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getSenha().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
