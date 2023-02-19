package portfolio.beom.service;


import portfolio.beom.argumentresolver.MemberSession;
import portfolio.beom.dto.request.LoginMemberRequest;
import portfolio.beom.dto.request.SaveMemberRequest;
import portfolio.beom.dto.request.UpdateMemberRequest;
import portfolio.beom.dto.response.LoginMemberResponse;
import portfolio.beom.dto.response.SaveMemberResponse;
import portfolio.beom.dto.response.UpdateMemberResponse;

public interface MemberService {


    SaveMemberResponse save(SaveMemberRequest request);

    LoginMemberResponse login(LoginMemberRequest loginMemberRequest);

    UpdateMemberResponse update(Long memberId, UpdateMemberRequest request);

    MemberSession getUser(String email);


    void delete(Long memberId);
}
