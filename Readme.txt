이 프로그램은 최인준, 김승재, 이지수, 한제규 4명이 작성한 교환학생 프로그램입니다.

프로그램 실행은 Exchange Program.exe를 이용해 실행할 수 있으며
project ver1.1 폴더는 이 프로그램의 이클립스 프로젝트가 들어있는 폴더입니다.

1. 로그인에 관하여
현재 DB에 저장되어있는 사용자는 학생 2명, 관리자 1명입니다.
이 정보는 Login DB.txt에 저장되어있으며 각각

	ID	PW
학생	s1123	1123
학생	s1132	1132
관리자	a1124	1124

입니다.

이 데이터를 이용해 로그인을 할 수도 있고 화면 하단의 비회원 로그인으로 손님으로 입장할 수 있습니다.

각 계정별로 실행할 수 있는 권한이 다르며 이는 아래에서 설명하겠습니다.

2. 각 기능에 대하여

1) 모집공고 조회
- 모든 사용자가 사용가능한 기능이며 모집공고가 존재하면 좌측리스트에 모집공고의 제목이 표시됩니다. 이 제목을 클릭하면 우측에 이 모집공고에 대한 자세한 내용이 표시되며 학생일 경우 그 모집공고에 대한 응시원서 작성이 가능합니다.

2) 진행상황 조회
- 학생만 사용가능한 기능이며 학생 별로 다른 결과가 나타납니다. 현재 접속한 학생이 어떤 모집공고에 대해 응시원서를 작성했다면 그에 대한 진행상황을 조회하고 최종합격 등록 및 삭제를 할 수 있습니다.

3) 이수학점 관리
- 학생만 사용가능한 기능이며 학생 별로 다른 결과가 나타납니다. 현재 접속한 학생이 이전에 갔다온 교환학생 프로그램에 대한 이수학점이 존재하면 그 이수학점에 대해 등록신청을 할 수 있습니다.

4) 등록학점 조회
- 학생만 사용가능한 기능이며 학생 별로 다른 결과가 나타납니다. 이수학점 관리에서 신청한 학점이 제대로 등록됬는지 확인할 수 있습니다.

5) 파견실적 조회
- 시간이 부족하여 구현하지 못했습니다. 양해 부탁드립니다.

6) 질문게시판
- 모든 사용자가 사용 가능한 기능이며 세부적으로는 QnA 게시판과 FAQ 게시판이 있습니다. 학생은 QnA게시판에 질문을 등록할 수 있으며 관리자는 FAQ를 등록하고 학생이 올린 질문에 답변할 수 있습니다.

7) 모집공고 작성
- 관리자만 사용가능한 기능입니다. 새로운 모집공고를 작성할 수 있습니다.

8) 모집공고 삭제
- 관리자만 사용가능한 기능입니다. 이전에 작성한 모집공고를 삭제할 수 있습니다.

3. 간단한 기능 사용 예시

1) 교환학생 프로그램 응시
관리자 로그인 -> 모집공고 작성 -> 로그아웃 -> 학생 로그인 -> 모집공고 조회 -> 응시원서 작성 -> 로그아웃 -> (합격 발표 이후) 학생 로그인 -> 진행상황 조회 -> 최종합격등록

2) 이수학점 등록
학생 로그인 -> 이수학점 관리 -> 이수학점 등록 -> 등록학점 조회

3) QnA
학생 로그인 -> 질문게시판 -> 질문 작성 -> 로그아웃 -> 관리자 로그인 -> 질문게시판 -> 질문에 대한 답변 작성