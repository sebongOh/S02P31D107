package com.ssafy.learnacademy.vo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.ssafy.learnacademy.common.BaseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.multipart.MultipartFile
import java.util.stream.Collectors
import javax.persistence.*


@Entity
@Table(name="member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var memberId: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false, unique = true)
    var email: String? = null,

    @Column(nullable = false)
    private var password: String? = null,

    @Column(nullable = false)
    var address: String? = null,

    @Column(nullable = false)
    var phone: String? = null,

    @Column(nullable = false)
    var age: Int? = null,

    @Column(nullable = false)
    var gender: Byte? = null,

    @Column(nullable = false)
    var profileUrl: String? = null,

    @Column(nullable = false)
    var type: String? = null,

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL])
    @JsonIgnore
    var memberSchedule : MutableList<MemberSchedule>? =null,

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL])
    @JsonIgnore
    var memberAcademy : MutableList<MemberAcademy>? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableList<String> = mutableListOf(),

    @Transient
    @JsonIgnore
    var profileFile: MultipartFile? = null

) : BaseEntity(), UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return roles.stream().map { role: String? -> SimpleGrantedAuthority(role) }.collect(Collectors.toList())
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String? {
        return email
    }

    override fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }


}
