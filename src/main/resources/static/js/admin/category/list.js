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

class CheckboxRenderer {
    constructor(props) {
        const { grid, rowKey } = props;

        const label = document.createElement('label');
        label.className = 'checkbox tui-grid-row-header-checkbox';
        label.setAttribute('for', String(rowKey));

        const hiddenInput = document.createElement('input');
        hiddenInput.className = 'hidden-input';
        hiddenInput.id = String(rowKey);

        const customInput = document.createElement('span');
        customInput.className = 'custom-input';

        label.appendChild(hiddenInput);
        label.appendChild(customInput);

        hiddenInput.type = 'checkbox';
        label.addEventListener('click', (ev) => {
            ev.preventDefault();

            if (ev.shiftKey) {
                grid[!hiddenInput.checked ? 'checkBetween' : 'uncheckBetween'](rowKey);
                return;
            }

            grid[!hiddenInput.checked ? 'check' : 'uncheck'](rowKey);
        });

        this.el = label;

        this.render(props);
    }

    getElement() {
        return this.el;
    }

    render(props) {
        const hiddenInput = this.el.querySelector('.hidden-input');
        const checked = Boolean(props.value);

        hiddenInput.checked = checked;
    }
}

const grid = new tui.Grid({
    el: document.getElementById('table'),
    scrollX: false,
    scrollY: false,
    rowHeaders: [
        { type: 'checkbox',
            renderer: {
                type: CheckboxRenderer
            }
        },
        { type: 'rowNum', width: 100, align: 'right'},
    ],
    columns: [
        {
            header: '카테고리',
            name: 'category',
            width: 300,
            editor: {
                type: 'text',
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
            width: 200,
            formatter: 'listItemText',
            editor: {
                type: 'select',
                options: {
                    listItems: [
                        { text: '익명', value: true },
                        { text: '실명', value: false }
                    ]
                }
            }
        },
        {
            header: '마지막 수정',
            name: 'last_update',
            width: 200,
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
        no: 2,
        category: 'thanks card',
        explanation: '감사인사를 전하는 게시판입니다',
        anonymous: false,
        last_update: '2022-05-15',
    }
];

grid.resetData(gridData);