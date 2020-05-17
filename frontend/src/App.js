import React, { Component } from 'react';
//import Button from 'antd/es/button';
import ReactDOM from 'react-dom';
import axios from 'axios';
import {
  Form,
  Input,
  Tooltip,
  Icon,
  Cascader,
  Select,
  Row,
  Col,
  Checkbox,
  Button,
  AutoComplete,
  DatePicker ,
    Alert
} from 'antd';


import moment from 'moment'
import './App.css';

const { Option } = Select;
const AutoCompleteOption = AutoComplete.Option;
const formTailLayout = {
  labelCol: { span: 4 },
  wrapperCol: { span: 8, offset: 4 },
};

const departments = [
  {
    value: 'Human Resource',
    label: 'Human Resource'

  },
  {
    value: 'Department2',
    label: 'Department2',
    children: [
      {
        value: 'Title2',
        label: 'Title2',
        
      },
    ],
  },
];




class RegistrationForm extends React.Component {
  state = {
    confirmDirty: false,
    autoCompleteResult: [],
    checked: false,
    disable: true,
    dropDownMenu:[
    ]
  };

  onSubmit = () => {

    axios.post(`http://localhost:8080/add`,{

      fullName: "Yasmin Marwan",
      birthDate: "2019-02-02",
      email: "y@mail.com",
      depName: "Human Resources",
      titleName: "HR Manager",
      isLeader:false

    }  )
        .then(res => {
          console.log(res);
          console.log(res.data);
        })
  };



  check = () => {
    this.props.form.validateFields(err => {
      if (!err) {
        console.info('success');
      }
    });
  };

  handleChange = e => {
    if(this.state.checked == true){
      this.setState({checked:false})
    }else{
      this.setState({checked:true})

    }
    this.setState(
        {
          checkNick: e.target.checked,
        },
    );
  };

  handleSubmit = e => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        //const depName = values.titleName[0];
        values.titleName = values.titleName[0];
        values.birthDate =  moment(values.birthDate).format("YYYY-MM-DD");
        //values.depName = depName;
        values.isLeader = this.state.checked;

        console.log('Received values of form: ', values);

        axios.post(`http://localhost:8080/add`,{
          birthDate: values.birthDate,
          // depName: values.depName,
          email: values.email,
          fullName: values.fullName,
          isLeader: values.isLeader,
          titleName: values.titleName
        }  )
            .then(res => {
              ReactDOM.render(
                  <div>
                    <Alert message="Employee Added Succesfully" type="success" />
                    {/*<Alert message="Info Text" type="info" />*/}
                    {/*<Alert message="Warning Text" type="warning" />*/}
                    {/*<Alert message="Error Text" type="error" />*/}
                  </div>,
                  document.getElementById('popup'),
              );
              console.log(res);
              console.log(res.data);
            })
            .catch(() => {
              ReactDOM.render(
                  <div>
                    {/*<Alert message="Employee Added Succesfully" type="success" />*/}
                    {/*<Alert message="Info Text" type="info" />*/}
                    {/*<Alert message="Warning Text" type="warning" />*/}
                    <Alert message="Error is found" type="error"/>
                  </div>,
                  document.getElementById('popup'),
              );
      }
            )

      }
    });
  };

  addTitlesAndDepartments = e =>{



    const array =[];

    axios.get(`http://localhost:8080/titles`)
        .then(res => {

          const titlesNames = res.data;
          titlesNames.forEach((item) => {
            const text = {
              value:item,
              label:item
            }
            array.push(text);
          })
          console.log(array);
          this.setState({dropDownMenu: array});
        })

    //console.log(departments);
    //console.log(array);

  };

  enableCheckBox = e =>{

    const array =[];

    axios.get(`http://localhost:8080/titlesObjects`)
        .then(res => {

          const titlesNames = res.data;
          titlesNames.forEach((item) => {
            console.log(item);
            if(item.name == e[0]){
              if(item.depID.employee == null){

               this.setState({disable:false});
              }
              else{
                console.log("Heloo");
                this.setState({disable:true});
              }
            }
            const text = {
              item
            }
            array.push(text);
          })
          console.log(array);
          //this.setState({dropDownMenu: array});
        })
        console.log(e);


  }





  render() {
    const config = {
      rules: [{ type: 'object', required: true, message: 'Please select time!' }],
    };
    const { getFieldDecorator } = this.props.form;
    const { autoCompleteResult } = this.state;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };

    const websiteOptions = autoCompleteResult.map(website => (
      <AutoCompleteOption key={website}>{website}</AutoCompleteOption>
    ));

    return (
      <Form {...formItemLayout} onSubmit={this.handleSubmit}>
        <Form.Item {...formItemLayout} label="FullName">
          {getFieldDecorator('fullName', {
            rules: [
              {
                required: true,
                message: 'Please input your name',
              },
            ],
          })(<Input placeholder="Please input your Full Name" />)}
        </Form.Item>

        <Form.Item label="E-mail">
          {getFieldDecorator('email', {
            rules: [
              {
                type: 'email',
                message: 'The input is not valid E-mail!',
              }, 
              {
                required: true,
                message: 'Please input your E-mail!',
              },
            ],
          })(<Input />)}
        </Form.Item>
        
        <Form.Item label="BirthDate">
          {getFieldDecorator('birthDate', config)(
            <DatePicker showTime format="YYYY-MM-DD" />,
          )}
        </Form.Item>
        
        <Form.Item label="Title">
          {getFieldDecorator('titleName', {

            rules: [
              { type: 'array', required: true, message: 'Please select your Title' },
            ],
          })(<Cascader options={this.state.dropDownMenu} onChange={this.enableCheckBox} onPopupVisibleChange= {this.addTitlesAndDepartments} /> )}
        </Form.Item>

        <Form.Item {...tailFormItemLayout}>

          <Checkbox checked={this.state.checkNick}  disabled={this.state.disable} onChange={this.handleChange}>
            Leader
          </Checkbox>

        </Form.Item>
         

        
        <Form.Item {...tailFormItemLayout}>
          <Button type="primary"  htmlType="submit" >
            Register
          </Button>

        </Form.Item>
        <div id = "popup">

        </div>
      </Form>
    );
  }
}



export default RegistrationForm;
