package com.portfolio.notepad.service;

import com.portfolio.notepad.controller.request.note.NoteCreateForm;
import com.portfolio.notepad.controller.request.note.NoteUpdateForm;
import com.portfolio.notepad.entity.Member;
import com.portfolio.notepad.entity.Note;
import com.portfolio.notepad.exception.MemberNotFount;
import com.portfolio.notepad.exception.NoteNotFound;
import com.portfolio.notepad.repository.MemberJpaRepository;
import com.portfolio.notepad.repository.NoteJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class NoteServiceImpl implements NoteService{

    private final NoteJpaRepository noteJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Override
    @Transactional
    public Note addNote(Long memberId, NoteCreateForm form) {
        Member findMember = memberJpaRepository.findById(memberId)
                .orElseThrow(MemberNotFount::new);

        return noteJpaRepository.save(new Note(findMember, form.getMemoType(), form.getTitle(), form.getDescription()));
    }

    @Override
    public Note getNote(Long noteId) {
        return noteJpaRepository.findById(noteId)
                .orElseThrow(NoteNotFound::new);
    }

    @Override
    public List<Note> getNotes(Long memberId) {
        return noteJpaRepository.findByMemberId(memberId);
    }

    @Override
    @Transactional
    public void editNote(Long noteId, NoteUpdateForm form) {
        Note findNote = noteJpaRepository.findById(noteId)
                .orElseThrow(NoteNotFound::new);

        findNote.updateNote(form);

    }

    @Override
    @Transactional
    public void deleteNote(Long noteId) {
        Note findNote = noteJpaRepository.findById(noteId)
                .orElseThrow(NoteNotFound::new);
        noteJpaRepository.delete(findNote);
    }
}
