package com.example.LabReservationProject.service;

import com.example.LabReservationProject.dto.UserDto;
import com.example.LabReservationProject.entity.BlackList;
import com.example.LabReservationProject.entity.User;
import com.example.LabReservationProject.repository.BlackListRepository;
import com.example.LabReservationProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j //로깅 어노테이션
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    //전체 테이블 조회
    public List<User> index() {
        return userRepository.findAll();
    }

    public User findByStringId(String id) {
        List<User> userList = index();
        User targetUser;

        for(User ul: userList) {
            if(id.equals(ul.getID())) {
                return ul;
            }
        }

        return null;
    }

    //계정 생성
    public User createUser(UserDto dto) {

        //dto를 entity로 변환
        User user = dto.toEntity();

        //id컬럼이 pk이기때문에 중복생성은 안되지만 덮어씌워지기때문에 예외처리해야함
        //1. 요청에서 dto.getid랑 user테이블 싹다 뒤져서 getid한거랑 같은거 있는지 비교
        int idCheck=0;
        List<User> userList = index();

        for(User ul: userList) {
            if(user.getID().equals(ul.getID())) {
                idCheck++;
            }
        }

        //check가 0보다 크면 하나이상 중복되는게 있다는뜻이므로 널 리턴
        if(idCheck > 0) {
            return null;
        }
        //id값이 널이면 널 리턴
        if(user.getID() == null) {
            return null;
        }
        //insert구문 실행
        return userRepository.save(user);
    }

    //로그인
    public User login(UserDto dto) {
        User user = dto.toEntity();
        //1. 전체 아디 가져오기
        List<User> userList = index();
        List<BlackList> blackLists = blackListRepository.findAll();

        // 계정 정지상태인지 판단해야함
        for(BlackList black : blackLists) {
            if (user.getID().equals(black.getID()) && !black.getRestrictionEndDate().equals(null)) {
                //반환으로 뭘보내야할지 아직 결정안됨, 그냥 null보내면 id나 패스워드 틀려서 로그인 못하는거랑 구분 불가능
                    return null;
            }
        }

        //2. 비교
        for(User ul: userList) {
            if(user.getID().equals(ul.getID()) && user.getPassword().equals(ul.getPassword())) {

                //3. 아디,비번 일치하면 그 계정 반환
                return ul;
            }
        }
        //없는 아이디 or 비번 틀림
        return null;

    }

    //토큰 입력해서 허가상태로 변경
    public User permission(String id) {
        List<User> userList = index();
        UserDto dto;

        for(User ul: userList) {
            if(id.equals(ul.getID())) {
                dto = UserDto.createUserDto(ul);
                dto.setPermissionState(true);
                User updatedUser = dto.toEntity();
                return userRepository.save(updatedUser);
            }
        }
        return null;
    }

    //신고 3회 누적으로 사용 정지
    public User nonPermission(String id) {
        List<User> userList = index();
        UserDto dto;

        for(User ul: userList) {
            if(id.equals(ul.getID())) {
                dto = UserDto.createUserDto(ul);
                dto.setPermissionState(false);
                User updatedUser = dto.toEntity();
                return userRepository.save(updatedUser);
            }
        }

        return null;
    }

    //개인정보 수정
    public User update(String id, UserDto editedDto) {
        User editedUser = editedDto.toEntity();
        User targetUser = findByStringId(id);

        if(targetUser == null || !id.equals(editedUser.getID())) {
            return null;
        }
        targetUser.userPatch(editedUser);
        return userRepository.save(targetUser);
    }

    // 계정 삭제
    public User delete(String id) {
        User targetUser = findByStringId(id);
        userRepository.delete(targetUser);
        return targetUser;
    }

    //사용자 1명 조회
    public User showUser(String id) {

        User user = findByStringId(id);
        return user;
    }

    // 모든 사용자 조회
    public List<User> indexUser() {
        return userRepository.findAll();
    }
}
