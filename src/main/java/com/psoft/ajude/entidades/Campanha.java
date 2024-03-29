package com.psoft.ajude.entidades;

import com.psoft.ajude.util.Util;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "Id da campanha.")
    private String id;
    @ApiModelProperty(value = "Nome curto da campanha.")
    private String nomeCurto;
    @ApiModelProperty(value = "descricao da campanha.")
    private String descricao;
    @ApiModelProperty(value = "Data final da campanha.")
    private Date deadline;
    @ApiModelProperty(value = "Valor que a campanha deseja alcançar.")
    private Double meta;
    @ManyToOne
    @ApiModelProperty(value = "Usuario dono da campanha.")
    private Usuario usuarioDono;
    @ApiModelProperty(value = "Define se a campanha está encerrada.")
    private boolean isEncerrada;
    @OneToMany(mappedBy = "campanha")
    @ApiModelProperty(value = "Doacoes que a campanha recebeu.")
    private List<Doacao> doacoes;
    @OneToMany(mappedBy = "campanha")
    @ApiModelProperty(value = "Comentarios da campanha.")
    private List<Comentario> comentarios;
    @ManyToMany
    @ApiModelProperty(value = "Curtidas que a campanha recebeu.")
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
        return this.getURL() == campanha.getURL();
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.getURL());
    }

    public String getURL() {
        return Arrays.stream(this.nomeCurto.split(" "))
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .map(Util::removerCaracteresDesnecessarios)
                .collect(Collectors.joining("-"));
    }

    public Double getQuantiaFaltante() {
        return this.getMeta() - this.getQuantidadeDoacoes();
    }

    public boolean isAtiva() {
        return getStatusCampanha().equals(StatusCampanha.ATIVA);
    }

    public void removerComentario(Comentario comentarioPai) {
        getComentarios().remove(comentarioPai);
    }
}
