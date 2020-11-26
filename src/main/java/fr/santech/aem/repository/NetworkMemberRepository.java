package fr.santech.aem.repository;

import fr.santech.aem.domain.NetworkMember;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the NetworkMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NetworkMemberRepository extends JpaRepository<NetworkMember, Long> {
}
