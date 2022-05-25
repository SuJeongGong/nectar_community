class CustomTextEditor {
    constructor(props) {
        const el = document.createElement('input');
        const {maxLength} = props.columnInfo.editor.options;

        el.type = 'text';
        el.maxLength = maxLength;
        el.value = String(props.value);

        this.el = el;
    }

    getElement() {
        return this.el;
    }

    getValue() {
        return this.el.value;
    }

    mounted() {
        this.el.select();
    }
}

const grid = new tui.Grid({
    el: document.getElementById('table'),
    scrollX: false,
    scrollY: false,
    columns: [
        {
            header: '번호',
            name: 'no'
        },
        {
            header: '카테고리',
            name: 'category',
            editor: {
                type: CustomTextEditor,
                options: {
                    maxLength: 10
                }
            }
        },
        {
            header: '설명',
            name: 'explanation',
            editor: {
                type: CustomTextEditor,
                options: {
                    maxLength: 10
                }
            }
        },
        {
            header: '익명',
            name: 'anonymous',
            formatter: 'listItemText',
            editor: {
                type: 'select',
                options: {
                    listItems: [
                        { text: '익명', value: true },
                        { text: '실명', value: false }
                    ]
                }
            },
            copyOptions: {
                useListItemText: true // when this option is used, the copy value is concatenated text
            }
        },
        {
            header: '마지막 수정',
            name: 'last_update'
        }
    ]
});

tui.Grid.applyTheme('clean');

grid.on('beforeChange', ev => {
    console.log('before change:', ev);
});

grid.on('afterChange', ev => {
    console.log('after change:', ev);
})


const gridData = [
    {
        no: 1,
        category: '익명 게시판',
        explanation: '아이디나 닉네임을 밝히지 않고, 익명으로 소통하는 게시판입니다.',
        anonymous: true,
        last_update: '2022-05-12',
    },
    {
        no: 1,
        category: 'thanks card',
        explanation: '감사인사를 전하는 게시판입니다',
        anonymous: false,
        last_update: '2022-05-15',
    }
];

grid.resetData(gridData);