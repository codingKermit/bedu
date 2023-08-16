<!-- 작성자 원준용 -->


<template>
    <div>
        <h2 id="review-write">수강후기 작성</h2>
        <b-form @submit.prevent="submitReview">
            <b-row>
                <b-col>
                    <b-form-group
                    description="대분류를 선택해주세요"
                    label="대분류">
                        <b-form-select v-model="currentTop" @input="this.currentMid = null" required>
                            <template #first>
                                <b-form-select-option disabled>대분류</b-form-select-option>
                            </template>
                            <b-form-select-option v-for="(item, index) in topCate" :key="index" :value="item.lectTopCate">{{ item.lectTopCateKor }}</b-form-select-option>
                        </b-form-select>
                    </b-form-group>
                </b-col>
                <b-col>
                    <b-form-group
                    description="중분류를 선택해주세요"
                    label="중분류">
                        <b-form-select v-model="currentTop" @input="this.currentMid = null" required>
                            <template #first>
                                <b-form-select-option disabled>중분류</b-form-select-option>
                            </template>
                            <b-form-select-option v-for="(item, index) in topCate" :key="index" :value="item.lectTopCate">{{ item.lectTopCateKor }}</b-form-select-option>
                        </b-form-select>
                    </b-form-group>
                </b-col>
                <b-col>
                    <b-form-group
                    description="소분류를 선택해주세요"
                    label="소분류">
                        <b-form-select v-model="currentTop" @input="this.currentMid = null" required>
                            <template #first>
                                <b-form-select-option disabled>소분류</b-form-select-option>
                            </template>
                            <b-form-select-option v-for="(item, index) in topCate" :key="index" :value="item.lectTopCate">{{ item.lectTopCateKor }}</b-form-select-option>
                        </b-form-select>
                    </b-form-group>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    
                </b-col>
            </b-row>
            <div class="form-group">
                <label id="review-label" for="rwGrade">별점</label>
            <div class="star-rating">
                <span v-for="rwGrade in 5" :key="rwGrade" @click="setStars(rwGrade)" :class="{ 'filled': rwGrade <= reviews.rwGrade }">&#9733;</span>
            </div>
            </div>
            <button id="review-WriteBtn" type="submit">작성 완료</button>
        </b-form>
    </div>
</template>

<script>
    import '@/assets/css/reviewStyle.css';

    export default {
        data() {
            return {
                reviews: {
                    title: '',
                    content: '',
                    rwGrade: 0,
                }
            };
        },
        methods: {
            submitReview() {
                const reviewData = {
                    title: this.reviews.title,
                    writer: this.reviews.writer,
                    rwGrade: this.reviews.rwGrade
                };

                this.$axiosSend("post", "/api/reviews/write", reviewData)
                    .then(response => {
                        console.log(response);
                        // 요청이 성공적으로 완료된 후 후기 목록 페이지로 리디렉션
                        this.$router.push('/review');
                    })
                    .catch(error => {
                        // 요청 실패 시 에러 처리
                        console.error(error);
                    });
            },
            setStars(rwGrade) {
                this.reviews.rwGrade = rwGrade;
            }
        }
    }
</script>