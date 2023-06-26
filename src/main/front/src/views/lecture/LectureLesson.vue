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
                        <p class="offcanvas-title fs-3 fw-bold text-truncate d-inline-block" id="lesson-list-Label">{{ lessonInfo.lectDtlTitle }}</p>
                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <div class="dropdown mt-3">
                            <ul class="list-unstyled">
                                <li v-for="(item, index ) in lessonList" :key="index" class="mb-3 ms-3">
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
                <div class="ratio ratio-16x9" >
                    <video @loadedmetadata="getMaxTime" :src="lessonInfo.lessonUrl" @timeupdate="updateProgressSituation" ref="bedu_video"></video>
                    <!-- 비디오 컨트롤러 -->
                    <div class="bedu-video-controls-container text-light text-opacity-75" >
                        <div class="w-100 h-100 d-block" @click="playToggleCenter">
                            <span class="bedu-video-center-toggle">
                                <font-awesome-icon 
                                :icon="playPauseToggle? 'fa-solid fa-pause':'fa-solid fa-play'" class="fs-1"

                                />
                            </span>
                        </div>
                        <div class="bedu-video-controls">
                            <div class="bedu-video-progress">
                                    <input id="bedu-video-progress-bar" class="w-100 h-100" type="range" :max="maxTime" min="0" step="1"
                                    v-model="currentTime" @input="progressUpdate">
                            </div>
                            <div class="d-flex">
                                <div class="bedu-video-buttons">
                                    <font-awesome-icon class="p-3 fs-4" 
                                    :icon="playPauseToggle ? 'fa-solid fa-pause':'fa-solid fa-play'"
                                    role="button" @click="playToggle" ref="bedu_video_play_pause"/>
                                </div>
                                <div class="bedu-video-volume cursor-pointer d-flex"
                                @mouseover="volumeSliderToggleOn"
                                @mouseleave="volumeSliderToggleOff">
                                    <font-awesome-icon class="fs-4 p-3" 
                                    :icon="muteToggle? 'fa-solid fa-volume-high':'fa-solid fa-volume-xmark'"
                                    @click="muteToggleFunc"
                                    />
                                    <div class="align-self-center" ref="volume_slider"
                                    :class="volumeSliderOver? 'bedu-video-volume-slider-container-over':'bedu-video-volume-slider-container'">
                                        <input type="range" 
                                        :class="volumeSliderOver? 'bedu-video-volume-slider-input-over':'bedu-video-volume-slider-input'"
                                        v-model="volume" @input="volumeToggle"/>
                                    </div>
                                </div>
                                <div 
                                ref="timestamp"
                                :class="volumeSliderOver? 'bedu-video-timestamp-over':'bedu-video-timestamp'">
                                    <div class="p-3">
                                        {{ currentTimeForUser }}&nbsp;/
                                        {{ maxTimeForUser }}
                                    </div>
                                </div>
                                <div class="ms-auto" @click="fullscreenToggle">
                                    <font-awesome-icon class="fs-4 p-3 cursor-pointer" :icon="['fas', 'expand']" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </b-container>
            
            <!-- 하단 네비 -->
            <b-container class="text-light" v-if="lessonList.length">
                <div class="row">
                <div class="col"></div>
                <div class="col d-flex m-auto">
                    <div v-if="lessonInfo.lectDtlIndex > 1" class="w-100">
                        <b-button class="rounded-5 p-3 bedu-bg-custom-blue w-100"
                        :to="'/lectureLesson?lectDtlNum='+lessonList[lessonInfo.lectDtlIndex-2].lectDtlNum">
                            <font-awesome-icon class="px-2" :icon="['fas', 'left-long']" />
                            <span>{{ lessonList[lessonInfo.lectDtlIndex-2].lectDtlIndex }}.{{ lessonList[lessonInfo.lectDtlIndex-2].lectDtlTitle }}</span>
                        </b-button>
                    </div>
                    
                    <div v-if="lessonInfo.lectDtlIndex < lessonList.length" class="w-100">
                        <b-button class="rounded-5 p-3 ms-3 bedu-bg-custom-blue w-100"
                        :to="'/lectureLesson?lectDtlNum='+lessonList[lessonInfo.lectDtlIndex].lectDtlNum">
                            <span>{{ lessonList[lessonInfo.lectDtlIndex].lectDtlIndex }}.{{ lessonList[lessonInfo.lectDtlIndex].lectDtlTitle }}</span>
                            <font-awesome-icon class="px-2" :icon="['fas', 'right-long']" />
                        </b-button>
                    </div>
                    <div class="position-absolute">
                    </div>
                </div>
                <div class="col text-end">
                    <b-button class="bedu-custom-yellow border rounded-5 p-3">
                        수강 완료
                    </b-button>
                </div>
            </div>
            </b-container>
        </div>
    </b-container>
</template>

<script>
import '@/assets/css/lectureStyle.css'
import '@/assets/css/common.css'

const baseUrl = "http://172.30.1.85:8081/";

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
            dummy:{
                url : "http://127.0.0.1:8081//94dc2841-4c9f-4afe-ac3f-c5bfbf5026a1.mp4"
            },
            playPauseToggle : false, // 플레이 버튼 토글
            maxTime : 0, // 동영상 맥스 시간 (초단위)
            currentTime : 0, // 동영상 현재 시간 (초단위)
            maxTimeForUser : "00:00", // 동영상 최대 시간 (분단위)
            currentTimeForUser : "00:00", // 동영상 현재 시간 (분단위)
            volume : 100, // 동영상 볼륨
            volumeSliderOver : false, // 볼륨 슬라이더 토글
            muteToggle : true, // 음소거 토글

        }
    },
    methods: {
        /** 강의 정보 및 수강 가능 여부 확인 */
        getLesson(){
            this.$axiosSend('get','/api/lect/getLesson',{
                num : this.lessonInfo.lectDtlNum
            })
            .then((res)=>{
                this.lessonInfo = res.data.lessonItem
                this.lessonInfo.lessonUrl = baseUrl+res.data.lessonItem.lessonUrl
                this.lessonList = res.data.lessonList
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        /** 사용자의 강의 가장 최근 수강 상태를 1초마다 업데이트 하는 메서드 */
        updateProgressSituation(e){
            let progress = Math.round(e.target.currentTime)
            this.currentTime = Math.round(e.target.currentTime) ;
            // console.log(this.currentTime)
            // console.log(this.maxTime)
            // console.log(this.lessonInfo.lectDtlNum + "동영상의 현재 재생 시간 " + progress + "초(s)")
            let minute = Math.round(this.currentTime / 60).toString().padStart(2,'0');
            const seconds = (this.currentTime%60).toString().padStart(2,'0') ;
            if(minute>=60){
                const hour = Math.round(minute/60).toString().padStart(2,'0');
                minute = Math.round(minute%60).toString().padStart(2,'0');
                this.currentTimeForUser = hour + ":" + minute + ":" + seconds
            } else{
                this.currentTimeForUser = minute + ":"+ seconds
            }
        },
        /** 동영상 플레이어 재생 버튼 토글 */
        playToggle(e){
            e.stopPropagation();
            let video = this.$refs.bedu_video;
            if(video.paused){
                e.className="pause"
                this.playPauseToggle = true;
                video.play()
            } else{
                e.className="play"
                this.playPauseToggle = false;
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
            this.$refs.bedu_video.requestFullscreen();
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
            this.volumeSliderOver = true;
        },
        volumeSliderToggleOff(){
            this.volumeSliderOver = false;
        },
        /** 음소거 토글 */
        muteToggleFunc(){
            if(this.muteToggle){
                this.muteToggle = false;
                this.$refs.bedu_video.muted=true
            } else{
                this.muteToggle = true;
                this.$refs.bedu_video.muted=false
            }
        },
        playToggleCenter(){
            
        }

        
    },
    mounted() {
        this.getLesson();
    },
    created() {
        this.lessonInfo.lectDtlNum = this.$route.query.lectDtlNum;
    },
    watch:{
        '$route.query.lectDtlNum':{
            immediate: true,
            handler(newNum){
                this.lessonInfo.lectDtlNum = newNum;
                this.getLesson();
            }
        }
    }
}

</script>

<style scoped>
    .bedu-video-controls{
        position: absolute;
        bottom: 0%;
        width: 100%;
        flex-wrap: wrap;
        background: rgba(0,0,0,0.3);
        transform: translateY(100%) translateY(-10px);
        transition: all 0.25s;
    }
    .bedu-video-volume-slider-container{
        overflow:hidden
    }

    .bedu-video-volume-slider-container-over, .bedu-video-volume-slider-input-over, .bedu-video-timestamp-over{
        overflow:hidden;
        transition: all 0.25s;
        transform: translateX(0);
    }

    .bedu-video-volume-slider-input, .bedu-video-timestamp{
        position: relative;
        transform: translateX(-100%);
        transition: all 0.25s;
    }
    .bedu-video-volume-slider-input::-webkit-slider-thumb{
        cursor: pointer;
        background: var(--blue);
    }

    #bedu-video-play-pause{
        background: none;
        border: 0;
        outline: 0;
        cursor: pointer;
    }
    .bedu-video-controls-container{
        overflow:hidden;
    }
    .bedu-video-controls-container:hover .bedu-video-controls{
        transform: translateY(0);
    }

    #bedu-video-progress-bar::-webkit-slider-thumb { 
        cursor: pointer;
        -webkit-appearance: none;
        width: 0px;
        height: 20px;
        box-shadow: -4000px 100px 0 4000px var(--yellow);
     }
     #bedu-video-progress-bar{
        cursor: pointer;
        overflow: hidden;
        -webkit-appearance: none; 
        height: 100px;
        background: rgba(255,255,255,0.7);
    }

    .bedu-video-center-toggle{
        position: relative;
        top : 48%;
        left : 48%;
    }
</style>