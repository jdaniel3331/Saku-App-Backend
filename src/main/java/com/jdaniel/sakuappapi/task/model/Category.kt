package com.jdaniel.sakuappapi.task.model

import com.jdaniel.sakuappapi.auth.model.User
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
    private var categoryId: Short? = null
    private var name: String? = null

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    private var tasks: MutableList<Task?>? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private var user: User? = null
}