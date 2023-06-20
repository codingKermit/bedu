<template>
    <div>
      <input list="emailList" v-model="emailValue"/>
      <datalist id="emailList">
        <option v-for="(email, index) in emailList" :value="email" :key="index" />
      </datalist>
    </div>
</template>
  
<script>
export default {
    name: 'EmailInput',
    data() {
        return {
        emailValue: '',
        emailList: [],
        };
    },
    watch: {
        emailValue(value) {
        const frequencyEmails = [
            '@naver.com',
            '@gmail.com',
            '@daum.net',
            '@hanmail.net',
            '@yahoo.com',
            '@outlook.com',
            '@nate.com',
            '@kakao.com',
        ];

        const userEmails = frequencyEmails.map((email) => {
            return value.includes('@') ? value.split('@')[0] + email : value + email;
        });

        this.emailList = userEmails;
        },
    },
};
</script>