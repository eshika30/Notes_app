package com.eshi.notes.repository;

import com.eshi.notes.model.notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<notes, Long> {
}

