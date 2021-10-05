<template>
    <div class="text-box">
        <p class="text">{{name}}</p>
        <img src="@/assets/ic_delete.png" :width="10" @click="onDelete"/>
    </div>
</template>
<script>
export default {
    name: 'TextBox',
    props: {
        name: String
    },
    setup(props, { emit }) {
        const onDelete = () => {
            emit('delete');
        };

        return { onDelete };
    }
}
</script>
<style scoped>
    .text-box {
        display: flex;
        align-items: center;
        border-radius: 5px;
        background-color: #eeeeee;
        padding: 5px 18px;
        min-width: 0;
        margin-right: 8px;
        margin-bottom: 8px;
    }
    .text {
        font-size: 14px;
        color: #898989;
        margin-right: 16px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
    img:hover {
        cursor: pointer;
    }
</style>