package com.jdaniel.sakuappapi.task.model

import com.jdaniel.sakuappapi.user.model.User
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Table(name = "categories", schema = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var categoryId: Long? = null
    var name: String? = null

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    var tasks: MutableList<Task?>? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
}