package com.portfolio.notepad.note.service;

import com.portfolio.notepad.login.MemberEntity;
import com.portfolio.notepad.note.NoteEntity;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface NoteService {

    /**
     * 메모 추가
     */
    public void addNote(NoteEntity note);

    /**
     * 메모 하나 가지고 오기
     * @return
     */
    public NoteEntity getNote(NoteEntity note);

    /**
     * 회원 아이디로 노트를 가지고 온다.
     * @param note 객체에 회원 아이디
     * @return 회원 아이디에 메모를 모두 가지고온다.
     */
    public List<NoteEntity> getNotes(NoteEntity note);

    /**
     * 메모 삭제
     */
    public void deleteNote(NoteEntity note);

}