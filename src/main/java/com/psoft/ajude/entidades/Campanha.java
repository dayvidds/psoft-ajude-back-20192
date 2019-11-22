package com.psoft.ajude.entidades;

import com.psoft.ajude.util.Util;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Campanha {

    @Id
    private String id;
    private String nomeCurto;
    private String descricao;
    private Date deadline;
    private Double meta;
    @ManyToOne
    private Usuario usuarioDono;
    private boolean isEncerrada;
    @OneToMany
    private List<Doacao> doacoes;
    @OneToMany
    private List<Comentario> comentarios;
    @OneToMany
    private Set<Usuario> likesUsuarios;

    public Campanha(String nomeCurto, String descricao, Date deadline, Double meta, Usuario usuarioDono) {
        this.nomeCurto = nomeCurto;
        this.descricao = descricao;
        this.deadline = deadline;
        this.meta = meta;
        this.usuarioDono = usuarioDono;

        this.isEncerrada = false;

        this.doacoes = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        this.likesUsuarios = new HashSet<>();

        this.id = getURL();
    }

    public String getId() {
        return id;
    }

    public String getNomeCurto() {
        return nomeCurto;
    }

    public void setNomeCurto(String nomeCurto) {
        this.nomeCurto = nomeCurto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public StatusCampanha getStatusCampanha() {
        StatusCampanha status = StatusCampanha.ATIVA;

        if (this.isEncerrada()) {
            status = StatusCampanha.ENCERRADA;
        } else if (this.isVencida()) {
            status = StatusCampanha.VENCIDA;
        } else if (this.isConcluida()) {
            status = StatusCampanha.CONCLUIDA;
        }

        return status;
    }

    private boolean isEncerrada() {
        return this.isEncerrada;
    }

    private boolean isVencida() {
        return this.deadlineConcluido() && !this.atingiuMeta();
    }

    private boolean isConcluida() {
        return this.deadlineConcluido() && this.atingiuMeta();
    }

    private boolean atingiuMeta() {
        return this.getQuantidadeDoacoes() > this.getMeta();
    }

    private boolean deadlineConcluido() {
        return new Date().after(this.getDeadline());
    }

    public Double getMeta() {
        return meta;
    }

    public void setMeta(Double meta) {
        this.meta = meta;
    }

    public Usuario getUsuarioDono() {
        return usuarioDono;
    }

    public void setUsuarioDono(Usuario usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    private Double getQuantidadeDoacoes() {
        return doacoes.stream().mapToDouble(Doacao::getQuantiaDoada).sum();
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public Set<Usuario> getLikesUsuarios() {
        return likesUsuarios;
    }

    public void adicionarDoacao(Doacao doacao) {
        this.doacoes.add(doacao);
    }

    public void adicionarComentario(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    public void toggleLike(Usuario usuario) {
        if (this.likesUsuarios.contains(usuario)) this.likesUsuarios.remove(usuario);
        else this.likesUsuarios.add(usuario);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campanha campanha = (Campanha) o;
        return id == campanha.id &&
                nomeCurto.equals(campanha.nomeCurto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCurto);
    }

    public String getURL() {
        return Arrays.stream(this.nomeCurto.split(" "))
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .map(Util::removerCaracteresDesnecessarios)
                .collect(Collectors.joining("-"));
    }
}
