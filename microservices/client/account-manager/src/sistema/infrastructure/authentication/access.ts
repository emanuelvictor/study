export class Access {

  private _access_token: string;
  private _expires_in: number;
  private _integrator: any;
  private _refresh_token: string;
  private _scope: any;
  private _token_type: any;

  get access_token(): string {
    return this._access_token;
  }

  set access_token(value: string) {
    this._access_token = value;
  }

  get expires_in(): number {
    return this._expires_in;
  }

  set expires_in(value: number) {
    this._expires_in = value;
  }

  get integrator(): any {
    return this._integrator;
  }

  set integrator(value: any) {
    this._integrator = value;
  }

  get refresh_token(): string {
    return this._refresh_token;
  }

  set refresh_token(value: string) {
    this._refresh_token = value;
  }

  get scope(): any {
    return this._scope;
  }

  set scope(value: any) {
    this._scope = value;
  }

  get token_type(): any {
    return this._token_type;
  }

  set token_type(value: any) {
    this._token_type = value;
  }

  get token(): string {
    return this.access_token
  }
}