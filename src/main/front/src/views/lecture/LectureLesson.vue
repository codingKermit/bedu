<template>
    <b-container class="py-5">
        <div class="mx-auto">
            <!-- 동영상 목록 콜랩스 -->
                <b-container>
                    <span type="button" data-bs-toggle="offcanvas" data-bs-target="#lesson-list" aria-controls="lesson-list">
                        <font-awesome-icon class="fs-1" :icon="['fas', 'bars']"/>
                    </span>
                <div class="offcanvas offcanvas-start " tabindex="-1" id="lesson-list" aria-labelledby="lesson-list-Label">
                    <div class="offcanvas-header">
                        <router-link class="offcanvas-title fs-3 fw-bold text-truncate d-inline-block text-decoration-none text-body" id="lesson-list-Label"
                        :to="{
                            name : 'lectureDetail',
                            query :{
                                num : lessonInfo.lectNum
                            }
                        }
                        "
                        >{{ lectInfo.title }}</router-link>
                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <div class="dropdown mt-3">
                            <ul class="list-unstyled">
                                <li v-for="(item, index ) in lessonList" :key="index" class="mb-3 ms-3 bedu-video-center-toggle-button">
                                    <b-link class="text-body text-decoration-none fs-6 d-flex" 
                                    :to="'/lectureLesson?lectDtlNum='+item.lectDtlNum">
                                        <p class="me-auto">
                                            {{ item.lectDtlIndex }}.{{ item.lectDtlTitle }}
                                        </p>
                                        <p class="ms-auto">{{ item.times }}</p>
                                    </b-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
               </b-container>
            <!-- 동영상 재생 컨테이너 -->
            <b-container class="mb-5">
                <p class="fs-2 fw-bold text-center">{{ lessonInfo.lectDtlTitle }}</p>
                <div class="ratio ratio-16x9" ref="bedu_video_container">
                    <video @loadedmetadata="getMaxTime" :src="lessonInfo.lessonUrl" @timeupdate="updateProgressSituation" ref="bedu_video"
                    controlsList="nodownload"
                    ></video>
                    <!-- 비디오 컨트롤러 -->
                    <div class="bedu-video-controls-container text-light text-opacity-75"
                    :class="fullscreenToggleData ? 'vh-100 vw-100':''"
                    >
                        <div class="w-100 h-100 d-block" @click="playToggleCenter">
                            <!-- 동영상 가운데 재생 토글 버튼 -->
                            <span class="bedu-video-center-toggle">
                                <font-awesome-icon 
                                :icon="playPauseToggleData? 'fa-solid fa-play':'fa-solid fa-pause'" class="fs-1"
                                :class="playToggleCenterData? 'play-toggle-center-appear':'play-toggle-center-disappear'"
                                />
                            </span>
                        </div>
                        <div class="bedu-video-controls">
                            <div class="bedu-video-progress">
                                    <input id="bedu-video-progress-bar" class="w-100 h-100" type="range" :max="maxTime" min="0" step="1"
                                    v-model="currentTime" @input="progressUpdate">
                            </div>
                            <div class="d-flex align-items-center">
                                <div class="bedu-video-buttons">
                                    <font-awesome-icon class="px-3 p-md-3 fs-4" 
                                    :icon="playPauseToggleData ? 'fa-solid fa-pause':'fa-solid fa-play'"
                                    role="button" @click="playToggle" ref="bedu_video_play_pause"/>
                                </div>
                                <div class="bedu-video-volume cursor-pointer d-flex"
                                @mouseover="volumeSliderToggleOn"
                                @mouseleave="volumeSliderToggleOff">
                                    <font-awesome-icon class="fs-4 px-1"
                                    :icon="muteToggleData? 'fa-solid fa-volume-high':'fa-solid fa-volume-xmark'"
                                    @click="muteToggleFunc"
                                    />
                                    <div class="align-self-center" ref="volume_slider"
                                    :class="volumeSliderOverData? 'bedu-video-volume-slider-container-over':'bedu-video-volume-slider-container'">
                                        <input type="range" 
                                        :class="volumeSliderOverData? 'bedu-video-volume-slider-input-over':'bedu-video-volume-slider-input'"
                                        v-model="volume" @input="volumeToggle"/>
                                    </div>
                                </div>
                                <div 
                                ref="timestamp"
                                :class="volumeSliderOverData? 'bedu-video-timestamp-over':'bedu-video-timestamp'">
                                    <div class="p-1 w-auto">
                                        {{ currentTimeForUser }} /
                                        {{ maxTimeForUser }}
                                    </div>
                                </div>
                                <div class="ms-auto" @click="fullscreenToggle">
                                    <font-awesome-icon class="fs-4 px-3 p-md-3 cursor-pointer" :icon="['fas', 'expand']" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </b-container>
            
            <!-- 하단 네비 -->
            <b-container class="text-light" v-if="lessonList.length">
                <!-- <div class="row"> -->
                <div class="d-flex">
                    <div class="d-flex gap-3 m-auto">
                        <div v-if="lessonInfo.lectDtlIndex > 1">
                            <b-button class="rounded-5 p-3 bedu-bg-custom-blue w-100"
                            :to="'/lectureLesson?lectDtlNum='+lessonList[lessonInfo.lectDtlIndex-2].lectDtlNum">
                                <font-awesome-icon class="px-2" :icon="['fas', 'left-long']" />
                                <span>{{ lessonList[lessonInfo.lectDtlIndex-2].lectDtlIndex }}.{{ lessonList[lessonInfo.lectDtlIndex-2].lectDtlTitle }}</span>
                            </b-button>
                        </div>
                        
                        <div v-if="lessonInfo.lectDtlIndex < lessonList.length">
                            <b-button class="rounded-5 p-3 bedu-bg-custom-blue w-100"
                            :to="'/lectureLesson?lectDtlNum='+lessonList[lessonInfo.lectDtlIndex].lectDtlNum">
                                <span>{{ lessonList[lessonInfo.lectDtlIndex].lectDtlIndex }}.{{ lessonList[lessonInfo.lectDtlIndex].lectDtlTitle }}</span>
                                <font-awesome-icon class="px-2" :icon="['fas', 'right-long']" />
                            </b-button>
                        </div>
                    </div>
                <!-- </div> -->
                <!-- <div class="col text-end">
                    <b-button class="bedu-custom-yellow border rounded-5 p-3">
                        수강 완료
                    </b-button>
                </div> -->
            </div>
            </b-container>
        </div>
    </b-container>
</template>

<script>
import '@/assets/css/lectureStyle.css'
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

/** 추후 FTP 서버 구축 후 변경 예정. 현재는 로컬에 저장 */
const baseUrl = "http://172.30.1.85:8081/LECT/VIDEO/";

export default{
    name : 'lectureLesson',
    data() {
        return {
            lessonInfo : {
                lectDtlIndex : 0,
                lectDtlNum : 0,
                lectDtlTitle : '',
                lectNum : 0,
                lessonUrl : '',
            },
            isAvailable : '',
            lessonList : [],
            lectInfo : {
                lectNum : 0,
                lectCateCode : '',
                lectCateKor : '',
            },
            playPauseToggleData : false, // 플레이 버튼 토글
            maxTime : 0, // 동영상 맥스 시간 (초단위)
            currentTime : 0, // 동영상 현재 시간 (초단위)
            maxTimeForUser : "00:00", // 동영상 최대 시간 (분단위)
            currentTimeForUser : "00:00", // 동영상 현재 시간 (분단위)
            volume : 100, // 동영상 볼륨
            volumeSliderOverData : false, // 볼륨 슬라이더 토글
            muteToggleData : true, // 음소거 토글
            playToggleCenterData : false, // 동영상 가운데 재생, 일시정지 나타나는 토글
            fullscreenToggleData : false, // 전체화면 토글 데이터
            subscribeInfo : '', // 멤버쉽 정보
            timer : null, // 페이지 접속 타이머
        }
    },
    methods: {
        makeToastForNext(){
            if(this.lessonInfo.lectDtlIndex < this.lessonList.length ){
                toast.success(`이어보기`,{
                transition: toast.TRANSITIONS.BOUNCE,
                position : toast.POSITION.BOTTOM_RIGHT,
                autoClose : false,
                multiple: false,
                onClick : () =>{
                    this.$routerPush('lectureLesson',{
                        'lectDtlNum' : this.lessonList[this.lessonInfo.lectDtlIndex].lectDtlNum
                    }, true)
                },
                })
            } else {
                toast.success('강의를 전부 수강하셨습니다!',{
                    position: toast.POSITION.BOTTOM_RIGHT,
                    multiple: false,
                })
            }


        },
        /** 강의 정보 및 수강 가능 여부 확인 */
        getLesson(){
            // view가 바뀌어도 alert이 중복되어서 나오는 문제 차단
            if(this.$route.name != 'lectureLesson'){
                return;
            }

            // 멤버쉽 정보 조회 API 호출 익명함수
            const membershipApi = async () => {
                return this.$axiosSend('get','/api/membership/getSubInfo',{
                    nickname : this.$store.getters.getNickname
                })
                .then((res)=>{
                    return Promise.resolve(res.data.item)
                })
                .catch((err)=>{
                    console.log(err)
                    return err
                })
            }

            // 수강중인 강의 정보 조회 API 호출 익명함수
            const signUpApi = async (subInfo) =>{
                return this.$axiosSend('get','/api/lect/getLesson',{
                    num : this.lessonInfo.lectDtlNum,
                    userName : userName,
                })
                .then((res)=>{
                    if(res.data.signUp == false && subInfo == null){
                        this.$swal({
                            title : '멤버쉽을 구독하고 모든 강의를 무제한으로 들어보세요',
                            icon : 'info',
                            text : '멤버쉽을 가입하면 최대 38% 할인된 가격으로 모든 강의를 들어볼 수 있어요!',
                            allowOutsideClick : false,
                            showCancelButton : true,
                            confirmButtonText : '멤버쉽 구독하기',
                            cancelButtonText : '뒤로가기',
                        })
                        .then((result)=>{
                            if(result.isConfirmed){
                                this.$routerPush('membership');
                            }else{
                                this.$routerPush('lectureDetail?num='+this.lessonInfo.lectNum);
                            }
                        })
                    }
                    this.lessonInfo = res.data.lessonItem;
                    this.lessonInfo.lessonUrl = baseUrl+res.data.lessonItem.lessonUrl;
                    this.lessonList = res.data.lessonList;
                    return res.data;
                })
                .catch((err)=>{
                    console.log(err)
                    return err
                })
            }
            


            const userName = this.$store.getters.getNickname;
            
            // 로그인 하지 않은 회원의 접근 차단
            if(userName == null) {
                this.$swal({
                    title:'지금 가입하세요',
                    icon : 'question',
                    text : 'B:EDU에 가입하시겠습니까?',
                    showCancelButton : true,
                    cancelButtonText : '아니오',
                    confirmButtonText : "예",
                    allowOutsideClick : false,
                })
                .then((result)=>{
                    if(result.isConfirmed){
                        this.$routerPush('regist')
                    } else{
                        this.$routerPush('/')
                    }
                })
                .finally(()=>{
                    return;
                })
                return;
            }

            // 비정상적인 방법의 접근 차단
            if(this.lessonInfo.lectDtlNum == undefined || this.lessonInfo.lectDtlNum == null){
                return
            }


            // 멤버쉽 가입 여부 체크 익명 함수 호출 => 동영상 결제 여부 & 동영상 정보 조회 익명 함수 호출
            membershipApi()
            .then((subInfo)=>{
                signUpApi(subInfo)
            })


        },
        /** 사용자의 강의 가장 최근 수강 상태를 1초마다 업데이트 하는 메서드 */
        updateProgressSituation(e){
            let progress = Math.round(e.target.currentTime)
            this.currentTime = Math.round(e.target.currentTime) ;
            let minute = Math.floor(this.currentTime / 60).toString().padStart(2,'0');
            const seconds = (this.currentTime%60).toString().padStart(2,'0') ;
            if(minute>=60){
                const hour = Math.round(minute/60).toString().padStart(2,'0');
                minute = Math.round(minute%60).toString().padStart(2,'0');
                this.currentTimeForUser = hour + ":" + minute + ":" + seconds
            }else{
                this.currentTimeForUser = minute + ":"+ seconds
            }
        },
        /** 동영상 플레이어 재생 버튼 토글 */
        playToggle(){
            let video = this.$refs.bedu_video;
            if(video.paused){
                this.playPauseToggleData = true;
                video.play()
            } else{
                this.playPauseToggleData = false;
                video.pause();
            }
        },
        /** 동영상 메타데이터 로드 완료 후 동영상 재생 시간 구하는 메서드 */
        getMaxTime(e){
            this.maxTime = Math.round(e.target.duration);
            let minute = Math.round(this.maxTime / 60);
            const seconds = this.maxTime%60;
            if(minute>=60){
                const hour = Math.round(minute/60).toString().padStart(2,'0');
                minute = Math.round(minute%60).toString().padStart(2,'0');
                this.maxTimeForUser = hour + ":" + minute + ":" + seconds
            } else{
                this.maxTimeForUser = minute + ":"+ seconds
            }

        },
        /** 컨트롤러의 재생바 값 변경시 동영상 재생시간 변경 메서드 */
        progressUpdate(e){
            this.$refs.bedu_video.currentTime = e.target.value
        },
        /** 전체화면 토글 */
        fullscreenToggle(){
            if(this.fullscreenToggleData){
                document.exitFullscreen();
                this.fullscreenToggleData = false;
            } else{
                this.$refs.bedu_video_container.requestFullscreen();
                this.fullscreenToggleData = true;
            }
        },
        /** 동영상 볼륨 변경 토글 */
        volumeToggle(e){
            // console.log(e.target.value)
            console.log(
                this.$refs.bedu_video.volume = e.target.value / 100
            )
        },
        /** 볼륨 슬라이더 마우스 오버 토글 */
        volumeSliderToggleOn(){
            this.volumeSliderOverData = true;
        },
        volumeSliderToggleOff(){
            this.volumeSliderOverData = false;
        },

        /** 음소거 토글 */
        muteToggleFunc(){
            if(this.muteToggleData){
                this.muteToggleData = false;
                this.$refs.bedu_video.muted=true
            } else{
                this.muteToggleData = true;
                this.$refs.bedu_video.muted=false
            }
        },
        /** 동영상 플레이어 재생/일시정지 시에 동영상 가운데 토글 이미지 */
        playToggleCenter(){
            let video = this.$refs.bedu_video;
            this.playToggleCenterData = true;
            setTimeout(()=>this.playToggleCenterData = false,750);
            if(video.paused){
                this.playPauseToggleData = true;
                video.play()
            } else{
                this.playPauseToggleData = false;
                video.pause();
            }
        },
        /** 강의 정보 조회 */
        getLectInfo(){
            this.$axiosSend('get','/api/lect/lectureDetail',{
                num : this.lessonInfo.lectNum
            })
            .then((res)=>{
                this.lectInfo = res.data
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        /** 브라우저 종료시 현재까지의 재생 정보 서버로 전송 */
        unloadEvent(){
            // console.log(this.lessonInfo)
            // console.log(this.lessonInfo.lectDtlNum)
            // console.log(this.currentTime)
            const userName = this.$store.getters.getNickname;
            const lectDtlNum = this.lessonInfo.lectDtlNum;
            const endTime = this.currentTime;
            this.$axiosSend('get','/api/lecture/history/save',{
                userName : userName,
                lectDtlNum : lectDtlNum,
                endTime : endTime,              
            })
        },
        /** 해당 페이지 접근 후 40초마다 현재 재생 정보 서버로 전송 */
        watchHistorySave(){
            this.timer = setInterval(()=>{
                this.$axiosSend('get','/api/lecture/history/save',{
                    userName : this.$store.getters.getNickname,
                    lectDtlNum : this.lessonInfo.lectDtlNum,
                    endTime : this.currentTime,
                })
            },10000)
        },
        
    },
    mounted() {
        window.addEventListener('beforeunload',this.unloadEvent);
        this.watchHistorySave();
    },
    created() {
        this.lessonInfo.lectNum = this.$route.query.lectNum;
        this.getLectInfo();
        this.lessonInfo.lectDtlNum = this.$route.query.lectDtlNum;
    },
    beforeUnmount(){
        // console.log(this.lessonInfo)
        // console.log(this.lessonInfo.lectDtlNum)
        // alert(this.lessonInfo.lectDtlNum)
        // alert(this.currentTime)
        this.unloadEvent();
        clearInterval(this.timer)
        window.removeEventListener('beforeunload',this.unloadEvent);
    },
    watch:{
        '$route.query.lectDtlNum':{
            immediate: true,
            handler(newNum){
                this.lessonInfo.lectDtlNum = newNum;
                this.playPauseToggleData = false;
                this.getLesson();
            },
        
        },
        currentTime (){
            if(this.currentTime == this.maxTime){
                this.makeToastForNext();
            }
        }
    }
}

</script>