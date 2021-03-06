# 몰입캠프 1주차 프로젝트 "몰입 뭐하지?"
## 팀원
* 정우진 | https://github.com/woojin7879
* 이지원 | https://github.com/Rudolf0328
```
몰입 캠프에 처음와 어쩔줄 몰라하는 우리의 모습에 영감을 받아 이 앱을 제작하게 되었다.
```
## 몰입 뭐하지? 앱 설명
<img src=https://user-images.githubusercontent.com/38205047/148054965-6ce525c8-eb5f-497f-9405-babe6e47c477.gif width="180" height="380"/>

```
Tab1 - 몰입 캠프를 위한 연락처
Tab2 - 몰입캠프 추억 기록을 위한 갤러리
Tab3 - 오늘 먹을 음식을 정해주는 기능과 정산을 위한 3가지 단계의 랜덤 정산 기능을 탑재
```

## 탭 상세 설명
### 탭1 - 몰입캠프 연락처
<img src=https://user-images.githubusercontent.com/38205047/148057480-6b5708f3-be46-4505-a359-d041e57c5a5c.jpg width="180" height="380"/>   <img src=https://user-images.githubusercontent.com/38205047/148058277-411039ca-23ba-467a-b841-c07fbe5f6d90.jpg width="180" height="380"/>   <img src=https://user-images.githubusercontent.com/38205047/148058340-de4857b3-b95e-42bc-958a-ed24e7c5b8e1.jpg width="180" height="380"/>   <img src=https://user-images.githubusercontent.com/38205047/148058387-de99adb3-d8f2-4a85-ab45-5d7b6e9b4a91.jpg width="180" height="380"/>   <img src=https://user-images.githubusercontent.com/38205047/148058433-8f6fb0b2-9f5d-42d1-95b8-18e464c17ab1.jpg width="180" height="380"/>

```
이 앱의 연락처는 휴대폰의 연락쳐와 분리된 저장소로 운영된다.
몰입캠프르 위하 연락처 앱으로 각 사람마다 이름, 전화번호, 분반을 저장할 수 있다.
연락처에 있는 정보를 불러올 수도 있고 앱을 통해 직접 정보를 입력하여 연락처를 만들 수도 있다.
연락처들은 ROOM을 이용해 내장 저장소에 저장된다.
앱이 실행되면 저장된 연락처들이 불려서 recycler view를 통해 화면에 표시된다.
각 연락처 옆의 전화 버튼, 메세지 버튼을 누르면 각각 핸드폰의 전화 앱, 메세지 앱을 연결된다.
각 연락처를 누르면 연락처의 상세 정보를 볼 수 있고 하단의 편집 버튼을 이용해 연락처 정보를 편집할 수 있다.
```

### 탭2 - 몰입캠프 갤러리
<img src=https://user-images.githubusercontent.com/38205047/148057477-ae1e3f2d-9fce-4d25-bbea-ce812694dd8b.jpg width="180" height="380"/>   <img src=https://user-images.githubusercontent.com/38205047/148059218-e38bafb5-6a32-4834-bb37-6e95730e3415.jpg width="180" height="380"/>   <img src=https://user-images.githubusercontent.com/38205047/148059490-6847b4c1-293a-4301-8135-f5c65d90d0e7.jpg width="180" height="380"/>

```
이 갤러리는 몰입캠프 추억을 담기위한 공간이다. 
탭2 역시도 탭1 과 같이 ROOM을 이용하여 휴대폰의 갤러리와 분리된 저장소로 운영된다.
갤러리 버튼을 클릭해 갤러리에서 여러분이 소장하고 싶은 이미즈를 불러오면 Recyclerview를 통해 GridLayout으로 이미지가 표시된다.
이미지를 클릭하면 Photoview를 이용해 확대 및 축소를 해볼 수 있다.
카메라 버튼을 누르면 카메라 앱을 호출하여 사진을 찍고 저장할 수 있다.
이미지를 꾹 누르면 이미지 삭제도 가능하다.
```

### 탭3 - 오늘 뭐먹지? 정산은 어떡하지?

#### 탭3-1. 오늘 뭐먹지?
<img src=https://user-images.githubusercontent.com/38205047/148056661-02179c4d-706e-44bb-8831-70b376d4c7c4.gif width="180" height="380"/>

```
탭3는 몰입캠프에 온 학생들이 뭘 먹을지 고민을 많이 하는 모습을 보고 만들게 되었다.
기본적인 컨셉은 배달의 민족의 앱에서 스와이프를 아래로하면 랜덤으로 음식을 정해주는 기능을 모티브로 삼았다.
오늘 뭐먹지 버튼을 클릭하면 "아래로 스와이프!" 라는 문구가 뜨고, 아래로 스와이프하면 음식 룰렛이 돌아간다.
음식은 37개의 데이터 중에 랜덤으로 하나가 선택되게된다.
음식이 정해지면 (예로 치킨이라하자) 네이버 API를 호출해 카이스트 근처 치킨집을 5곳을 추천해준다.
링크가 있는 음식점이라면, 음식점 이름을 클릭하면 링크로 이동 할 수 있다.
룰렛으로 선정된 음식이 마음에 들지 않는다면 다시 아래로 스와이프하여 룰렛을 다시 돌릴 수 있다.
```
#### 탭3-2. 정산은 어떡하지?
<img src=https://user-images.githubusercontent.com/38205047/148057222-06c30d79-53cb-4d39-83cf-01340926648f.gif width="180" height="380"/>

```
정산은 어떡하지 탭은 재미있게 랜덤으로 정산을 할 수있다.
기본적으로 처음에 금액과 사람 수를 정할 수 있다.
사람은 Recyclerview를 통해 보여주며 많아지면 스크롤이 가능하다.
정산하기를 클릭하면 입력된 금액 밑에 평균 금액이 뜨고 각 맛에 따라 다른방식으로 정산된다.
정산방식은 순한맛, 중간맛, 매운맛으로 나뉜다.
순한맛은 대부분의 사람들이 평균금액에 근접하게 정산이 된다.
중간맛은 금액이 요동치기 시작하며 평균금액에서 순한맛보다 심한 범위로 랜덤으로 정산이 되게 된다.
매운맛은 말그대로 한명 몰빵이다. 모든 금액을 단 한사람에게 몰아준다.
```

## 탭에 사용된 Technique
### 탭1 - 몰입캠프 연락처
```
* recycler view
* ROOM
* Glide
* 연락처 권한
```

### 탭2 - 몰입캠프 갤러리
```
* recycler view
* ROOM
* Glide
* Photoview
* 갤러리권한
* 카메라 권한
```
### 탭3 - 오늘 뭐먹지? 정산은 어떡하지?
```
* recycler view
* Naver Search API
* Animation
* Random
* Hyperlink
```
## 한줄평
### 정우진
```
처음 접하는 Android Studio라 어려웠지만 짧은 시간에 만족할만한 결과물은 만든것 같아 뿌듯하다.
```
### 이지원
```
내 잠과 바꾼 소중한 앱...
```
