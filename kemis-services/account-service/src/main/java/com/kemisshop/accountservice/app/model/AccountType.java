package com.kemisshop.accountservice.app.model;

import com.kemisshop.accountservice.app.dto.request.AccountTypeForm;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author wontgn
 * @created 2/16/21
 * @package com.kemisshop.accountservice.app.model
 */

@Entity
@EnableJpaAuditing
@SuppressWarnings("unchecked")
public class AccountType implements Serializable {

    private static final long serialVersionUID = -4550573824052372134L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "Public_ID", unique = true)
    private UUID  publicAccountTypeId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "accountType",fetch = FetchType.LAZY)
    Set<Account> accounts;

    @CreationTimestamp
    private Date createDayTime;

    @UpdateTimestamp
    private Date updateDayTime;

    public AccountType() {
    }

    private AccountType(String role) {
        this.publicAccountTypeId = UUID.randomUUID();
        this.role = Role.findByLabel(role);
        this.accounts = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public Date getCreateDayTime() {
        return createDayTime;
    }

    public UUID getPublicAccountTypeId() {
        return publicAccountTypeId;
    }

    public Date getUpdateDayTime() {
        return updateDayTime;
    }

    public static AccountType of(AccountTypeForm form) {
        return new AccountType(form.getRole());
    }
}
